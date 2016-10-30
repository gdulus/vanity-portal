<div class="stream" ng-controller="ListController">
    <ul>
        <li ng-repeat="comment in comments">
            <div class="clearfix content-heading">
                <p>
                    <asset:image src="icons/{{ comment.author.avatar }}" class="icon img-responsive pull-left"/>
                    {{ comment.text }}
                </p>

                <div class="controls pull-right">
                    <a href="#" class="button user">{{ comment.author.name }}<span>{{ comment.author.ranking }}</span></a>
                    <a href="#" class="button replay" ng-click="replay()">odrzuć kupę</a>
                </div>
            </div>
        </li>
    </ul>

    <a class="btn btn-default button-main center-block more" ng-click="loadMore()" ng-if="more">Wiecęcej</a>
</div>

<form role="form" class="form" ng-controller="FormController" ng-submit="submitComment()">
    <div class="form-group">
        <label class="sr-only" for="message">Kupa</label>
        <textarea id="message" class="form-control" rows="3" placeholder="..."></textarea>
    </div>
    <button type="submit" class="btn btn-default button-main pull-right">Rzuć kupą</button>
</form>
