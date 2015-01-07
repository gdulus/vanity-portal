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
 * Tracking
 */
V.Tracking = (function ($, undefined) {

    var BASE = '/api/tracking/';

    var track = function (type, id) {
        var url = BASE + type + '?id=' + id;
        V.Logger.info('Executing tracking resource ' + url);

        $.get(url)
            .done(function () {
                V.Logger.info('Tracking resource done for: ' + url);
            })
            .fail(function () {
                V.Logger.info('Tracking resource error for: ' + url);
            });
    };

    return {
        article: function (id) {
            track('article', id);
        },

        tag: function (id) {
            track('tag', id);
        }
    }

})(jQuery);