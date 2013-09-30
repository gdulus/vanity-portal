window.V = window.V || {};

/**
 * JQuery extensions
 */

$.fn.exists = function () {
    return this.length !== 0;
}

/**
 * Logger component
 */
V.Logger = (function (undefined) {

    var log = function (level, message) {
        if (console.log) {
            console.log('[' + level + ']: ' + message)
        }
    }

    return {
        info: function (message) {
            log('INFO', message);
        }
    }
})();

/**
 * Search component
 */
V.Search = (function (undefined) {

    var $searchInput = null;
    var $searchButton = null;
    var $resultContainer = null;
    var apiUrl = null;
    var hideAfter = 200;
    var searchBoxFocusSearchAfter = 500;
    var hide = false;
    var hideTimerHandler = null;
    var searchBoxFocusTimerHandler = null;
    var previousSearchQuery = null;

    var triggerSearch = function (value) {
        $.get(apiUrl, {term: value}, function (results) {
            V.Logger.info('Search for: ' + value);
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
            $resultContainer.show();
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
        // keyboard binding
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
                    window.location = articleUrl;
                    e.stopPropagation();
                    e.preventDefault();
                    break;
            }
        });
        // hide/show results
        //
        var mouseOut = function (e) {
            hide = true;
            hideTimerHandler = setTimeout(hideResult, hideAfter)
            e.stopPropagation();
        };

        var mouseOver = function (e) {
            hide = false;
            clearTimeout(hideTimerHandler);
            hideTimerHandler = null;
            e.stopPropagation();
        };

        $searchInput.mouseover(function (e) {
            if (hide) {
                triggerSearch(previousSearchQuery);
            }
            mouseOver(e);
        });
        $searchInput.mouseout(mouseOut);
        $resultContainer.mouseover(mouseOver);
        $resultContainer.mouseout(mouseOut);

        // search trigger
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
        // search button click
        $searchButton.click(function (e) {
            var url = $(this).attr('href');
            window.location.replace(url + '?term=' + $searchInput.val());
            e.stopPropagation();
            e.preventDefault();
        })
    }

    return {
        init: function (searchFormId) {
            if (!$searchInput) {
                var $searchForm = $(searchFormId)
                apiUrl = $searchForm.attr('target');
                $searchInput = $searchForm.find('input[type=search]');
                $searchButton = $searchForm.find('a');
                $resultContainer = $('<ul id="search-result"></ul>')
                $resultContainer.width($searchInput.outerWidth());
                $resultContainer.hide();
                $(document.body).append($resultContainer);
                bindEvents()
            }
        }
    }
})();