//= require vanity/base.js

window.V = window.V || {};

/**
 * Tracking
 */
V.Tracking = (function ($, undefined) {

    var BASE = '/api/tracking/';

    var track = function (type, id) {
        var url = BASE + type;
        V.Logger.info('Executing tracking resource ' + url);

        $.post(url, {id: id})
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
