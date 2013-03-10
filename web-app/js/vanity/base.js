var V = V || {};

V.Search = (function(){

    return {
        init: function(elementId, responseHandler){
            var searchElement = $(elementId);
            var searchElementInput= $(elementId + '-input');

            var source = searchElement.attr('href');
            var search = searchElement.autocomplete({
                autoFocus:true,
                source: source,
                minLength: 2,
                response: function(event, ui){
                    responseHandler.call();
                },
                search: function(event, ui) {
                    currentQuery = searchElement.val();
                },
                select: function(event, ui) {

                     window.location.href = ui.item.value;
                }
            });

            searchElementInput.keyup(function(){
                search.autocomplete('search', searchElementInput.val());
            });
        }
    }
})();