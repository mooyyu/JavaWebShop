<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-15
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-1" id="right_side">
    <div class="container-fluid py-4 border">
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">这</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">是</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">一</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">排</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">没</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">神</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">么</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">用</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">的</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">按</button>
        <button @click="getHitokoto" type="button" class="btn btn-primary my-2 d-block">扭</button>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="hitokotoModal" tabindex="-1" role="dialog" aria-labelledby="hitokotoModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="hitokotoModalLabel">你一定很无聊</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    {{hitokoto}}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var hitokotoapp = new Vue({
        el: "div#right_side",
        data: {
            hitokoto: ""
        },
        methods: {
            getHitokoto: function() {
                var that = this;
                axios.get('https://v1.hitokoto.cn?encode=text')
                    .then(function(res) {
                        that.hitokoto = res.data;
                        $('div#hitokotoModal').modal('show');
                    }).catch(function(error) {
                        console.info(error);
                });
            }
        }
    });
</script>