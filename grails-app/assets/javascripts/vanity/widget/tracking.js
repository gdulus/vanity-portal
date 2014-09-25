//= require vanity/base.js

window.V = window.V || {};

V.Tracking = (function (undefined) {
    return {
        init: function (path) {
            V.Logger.info('Tracking ' + path);
            var image = new Image();
            image.onload = function () {
                V.Logger.info('Tracking successful' + path);
            };
            image.src = path;
        }
    }
})();