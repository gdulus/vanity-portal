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
