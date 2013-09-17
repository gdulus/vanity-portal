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

    var $element = null

    var $resultContainer = null;

    var apiUrl = null

    var prevValue = null;

    var elementHeight = null

    var hideAfter = 100;

    var hide = false;

    var hideTimerHandler = null;

    var triggerSearch = function () {
        value = $element.val();
        if (value != prevValue) {
            V.Logger.info('Show result for "' + value + '" - "' + prevValue + '"');
            $.get(apiUrl, {term: value}, function (results) {
                // clear previous results
                $resultContainer.empty();
                // populate new one
                $.each(results, function (index, result) {
                    var cssClass = index == 0 ? 'class="selected"' : '';
                    $resultContainer.append('<li ' + cssClass + '><a href="' + result.link + '">' + result.label + '</a></li>')
                });
                // adjust position
                var offset = $element.offset();
                $resultContainer.offset({left: offset.left, top: offset.top + elementHeight});
                $resultContainer.show();
            });
        }

        prevValue = value;
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
        $element.data('timeout', null).keyup(function () {
            clearTimeout($element.data('timeout'));
            $element.data('timeout', setTimeout(triggerSearch, 200));
        });

        $element.keydown(function (e) {
            var keyCode = e.keyCode || e.which;
            switch (keyCode) {
                case 38: //up
                    var current = $resultContainer.find('.selected');
                    var selected = current.prev('li');
                    swapColors(current, selected);
                    e.stopPropagation();
                    e.preventDefault();
                    break;
                case 40: //down
                    var current = $resultContainer.find('.selected');
                    var selected = current.next('li');
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

        var mouseOver = function () {
            hide = true;
            hideTimerHandler = setTimeout(hideResult, hideAfter)
        };

        $element.mouseover(mouseOver);
        $resultContainer.mouseover(mouseOver);

        var mouseOut = function () {
            hide = false;
            clearTimeout(hideTimerHandler);
            hideTimerHandler = null;
        };

        $element.mouseout(mouseOut);
        $resultContainer.mouseout(mouseOut);
    }

    return {
        init: function (elementId) {
            if (!$element) {
                $element = $(elementId);
                apiUrl = $element.parent('form').attr('target');
                elementHeight = $element.outerHeight();
                $resultContainer = $('<ul id="search-result"></ul>')
                $resultContainer.hide();
                $resultContainer.width($element.outerWidth());
                $(document.body).append($resultContainer);
                bindEvents()
            }
        }
    }
})();