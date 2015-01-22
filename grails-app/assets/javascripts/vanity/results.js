//= require vanity/base.js
//= require vanity/widget/search.js
//= require vanity/widget/toggler.js
//= require vanity/widget/tracking.js

(function (undefined) {
    V.Search.init('#search-menu');
    V.Toggler.init('#search-menu', '#search-button', V.Search.focus, V.Search.clear);
    V.Toggler.init('#sub-menu', '#sub-menu-button', null, null);
})();