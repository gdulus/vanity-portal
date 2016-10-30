/**
 * Search component
 */
V.Toggler = (function ($, undefined) {
    return {
        init: function (menuSelector, menuButton, onShowCallback, onHideCallback) {
            V.Logger.info('Initializing toggler widget for ' + menuSelector + ' and ' + menuButton);
            var $menu = $(menuSelector);
            var $button = $(menuButton);

            var toggler = {
                hide: function () {
                    $menu.addClass('hidden');
                    $button.removeClass('selected');
                    onHideCallback && onHideCallback();
                },
                show: function () {
                    $menu.removeClass('hidden');
                    $button.addClass('selected');
                    onShowCallback && onShowCallback();
                }
            };

            $button.click(function () {
                V.Logger.info('Triggering toggle');
                var hidden = $menu.hasClass('hidden');

                if (hidden) {
                    toggler.show()
                }

                if (!hidden) {
                    toggler.hide();
                }
            });

            return toggler;
        }
    }
})(jQuery);