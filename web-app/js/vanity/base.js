window.V = window.V || {};

/**
 * Logger component
 */
V.Logger = (function(){

    var log = function(level, message){
        if (console.log){
            console.log('[' + level + ']: ' + message)
        }
    }

    return {
        info: function(message){
            log('INFO', message);
        }
    }
})();

/**
 * Search component
 */
V.Search = (function(){

    var prevValue = null;

    var $element = null

    var elementHeight = null

    var $apiUrl = null

    var $resultContainer

    var triggerSearch = function(){
        value = $element.val();
        if (value != prevValue){
            V.Logger.info('Show result for "' + value + '" - "' + prevValue + '"');
            $.get($apiUrl, {term: value}, function(results) {
                // clear previous results
                $resultContainer.empty();
                // populate new one
                $.each(results, function(index, result) {$resultContainer.append('<li><a href="' + result.value + '">' + result.label + '</a></li>')});
                // adjust position
                var offset = $element.offset();
                $resultContainer.offset({left:offset.left, top:offset.top + elementHeight});
                $resultContainer.show();
            });
        }

        prevValue = value;
    }

    function bindEvents($element){
        $element.data('timeout', null).keyup(function(){
            clearTimeout($element.data('timeout'));
            $element.data('timeout', setTimeout(triggerSearch, 200));
        });

        $element.keydown(function (e) {
            var keyCode = e.keyCode || e.which;
            switch (keyCode) {
                case 38: //up
                    V.Logger.info('up');
                    e.stopPropagation();
                break;
                case 40: //down
                    V.Logger.info('down');
                    e.stopPropagation();
                break;
              }
        });

        $element.blur(function(){
            $resultContainer.empty();
            $resultContainer.hide();
        })
    }

    return {
        init: function(elementId){
            if (!$element){
                $element = $(elementId);
                $apiUrl = $element.attr('href');
                elementHeight = $element.outerHeight();
                $resultContainer = $('<ul id="search-result"></ul>')
                $resultContainer.hide();
                $resultContainer.width($element.outerWidth());
                $(document.body).append($resultContainer);
                bindEvents($element)
            }
        }
    }
})();