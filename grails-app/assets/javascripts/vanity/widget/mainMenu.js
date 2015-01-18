/**
 * Search component
 */
V.MainMenu = (function ($, undefined) {
    var $submenu = null;
    var $submenuButton = null;

    return {
        init: function (submenuSelector, submenuButtonSelector) {
            V.Logger.info('Initializing main menu widget');
            $submenu = $(submenuSelector);
            $submenuButton = $(submenuButtonSelector);
            $submenuButton.click(function(){
                V.Logger.info('Toggle sub menu');
                $submenu.toggleClass('hidden')
            });
        }
    }
})(jQuery);