<v:withFeature name="social">
    <g:javascript>
        <social:i18n/>
        <social:vipData viewModel="${viewModel}"/>
    </g:javascript>
    <asset:javascript src="social/compiled/app.js"/>
    <g:javascript>social.core.init();</g:javascript>
</v:withFeature>