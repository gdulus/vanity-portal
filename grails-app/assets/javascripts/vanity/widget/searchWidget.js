/**
 * Search component
 */
V.Search = (function (undefined) {

    var $searchInput = null;
    var $searchButton = null;
    var $resultContainer = null;
    var apiUrl = null;
    var hideAfter = 200;
    var searchBoxFocusSearchAfter = 200;
    var termMinLength = 3;
    var hide = false;
    var hideTimerHandler = null;
    var searchBoxFocusTimerHandler = null;
    var previousSearchQuery = null;
    var cache = {};

    var getSearchTermQueryUrl = function () {
        var url = $searchButton.attr('href');
        var term = $searchInput.val();

        if (!term || term.length < termMinLength) {
            return null;
        }

        return url + '/?q=' + term;
    };

    var renderResults = function (results) {
        V.Logger.info('Rendering results');
        // remove loader
        $searchInput.removeClass('loader');
        // clear previous results
        $resultContainer.empty();
        // tags
        if (results.tags && results.tags.length > 0) {
            $resultContainer.append('<li class="tag summary">Nasze gwiazdy</a></li>')
            $.each(results.tags, function (index, result) {
                var cssClass = index == 0 ? ' selected' : '';
                $resultContainer.append('<li class="tag' + cssClass + '"><a href="' + result.link + '">' + result.label + '</a></li>')
            });
        }
        // articles
        if (results.articles && results.articles.length > 0) {
            $resultContainer.append('<li class="article summary">Ostatnie newsy</a></li>')
            $.each(results.articles, function (index, result) {
                var cssClass = (results.tags.length == 0 && index == 0) ? ' selected' : '';
                $resultContainer.append('<li class="article' + cssClass + '"><a href="' + result.link + '">' + result.label + '</a></li>')
            });
        }

        var offset = $searchInput.offset();
        $resultContainer.css('left', offset.left);
        $resultContainer.css('top', offset.top + $searchInput.outerHeight());
        $resultContainer.width($searchInput.outerWidth());
        $resultContainer.show();
    }

    var triggerSearch = function (value) {
        // trigger search only for not empty results
        if (!value) {
            return;
        }
        // check if value is present in cache
        if (cache[value]) {
            V.Logger.info('Cache hit for value: ' + value);
            renderResults(cache[value]);
            return;
        }
        // value not present in cache, execute ajax call
        V.Logger.info('Triggering ajax call for: ' + value);
        $searchInput.addClass('loader');
        $.get(apiUrl, {term: value}, function (results) {
            cache[value] = results;
            renderResults(results);
        });
    }

    var swapColors = function (current, selected) {
        if (!selected || !selected.exists()) {
            return;
        }

        current.removeClass('selected');
        selected.addClass('selected');
    }

    var hideResult = function () {
        if (hide) {
            $resultContainer.empty();
            $resultContainer.hide();
        }

        clearTimeout(hideTimerHandler);
        hideTimerHandler = null;
    }

    function bindEvents() {
        V.Logger.info('Start binding events');
        V.Logger.info('Keyboard bindings');
        $searchInput.keydown(function (e) {
            var keyCode = e.keyCode || e.which;
            switch (keyCode) {
                case 38: //up
                    var current = $resultContainer.find('.selected');
                    var selected = current.prev('li');

                    if (selected.hasClass('summary')) {
                        selected = selected.prev('li')
                    }

                    swapColors(current, selected);
                    e.stopPropagation();
                    e.preventDefault();
                    break;
                case 40: //down
                    var current = $resultContainer.find('.selected');
                    var selected = current.next('li');

                    if (selected.hasClass('summary')) {
                        selected = selected.next('li')
                    }

                    swapColors(current, selected);
                    e.stopPropagation();
                    e.preventDefault();
                    break;
                case 13: //enter
                    var current = $resultContainer.find('.selected a');
                    var articleUrl = current.attr('href');

                    if (articleUrl) {
                        window.location.href = articleUrl;
                    } else {
                        var url = getSearchTermQueryUrl();
                        if (url) {
                            window.location.replace(url);
                        }
                    }

                    e.stopPropagation();
                    e.preventDefault();
                    break;
            }
        });
        // hide/show results
        //
        var mouseOut = function (e) {
            hide = true;
            hideTimerHandler = setTimeout(hideResult, hideAfter);
            e.stopPropagation();
        };

        var mouseOver = function (e) {
            hide = false;
            clearTimeout(hideTimerHandler);
            hideTimerHandler = null;
            e.stopPropagation();
        };

        V.Logger.info('Search input bindings');
        $searchInput.mouseover(function (e) {
            if (hide) {
                triggerSearch(previousSearchQuery);
            }
            mouseOver(e);
        });
        $searchInput.mouseout(mouseOut);
        $searchInput.keyup(function () {
            clearTimeout(searchBoxFocusTimerHandler);
            searchBoxFocusTimerHandler = setTimeout(function () {
                var searchQuery = $searchInput.val();
                if (previousSearchQuery != searchQuery) {
                    triggerSearch($searchInput.val());
                }
                previousSearchQuery = searchQuery;
            }, searchBoxFocusSearchAfter);
        });

        V.Logger.info('Result container bindings');
        $resultContainer.mouseover(mouseOver);
        $resultContainer.mouseout(mouseOut);

        V.Logger.info('Search button bindings');
        $searchButton.click(function (e) {
            var url = getSearchTermQueryUrl();

            if (url) {
                window.location.replace(url);
            }

            e.stopPropagation();
            e.preventDefault();
        });

        V.Logger.info('Events initialized');
    }

    return {
        init: function (searchFormId) {
            if (!$searchInput) {
                V.Logger.info('Initializing search widget');
                var $searchForm = $(searchFormId).find('form');
                apiUrl = $searchForm.attr('action');
                $searchInput = $searchForm.find('input[type=search]');
                $searchButton = $searchForm.find('input[type=submit]');
                $resultContainer = $('<ul id="search-result"></ul>')
                //$resultContainer.hide();
                $(document.body).append($resultContainer);
                bindEvents();
                V.Logger.info('Search widget initialized for endpoint ' + apiUrl);
            }
        }
    }
})();