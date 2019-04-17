<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-17
  Time: 00:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav aria-label="...">
    <ul class="pagination justify-content-center">
        <li v-bind:class="prev_page">
                <span class="page-link" aria-label="Previous" v-on:click="prevpage">
                  <span aria-hidden="true">&laquo;</span>
                  <span class="sr-only">Previous</span>
                </span>
        </li>

        <li id="link_1" v-on:click="page_link(-2)" class="page-item"><span class="page-link">{{link_1}}</span></li>
        <li id="link_2" v-on:click="page_link(-1)" class="page-item"><span class="page-link">{{link_2}}</span></li>
        <li id="link_3" class="page-item active"><span class="page-link">{{link_3}}</span></li>
        <li id="link_4" v-on:click="page_link(1)" class="page-item"><span class="page-link">{{link_4}}</span></li>
        <li id="link_5" v-on:click="page_link(2)" class="page-item"><span class="page-link">{{link_5}}</span></li>

        <li v-bind:class="next_page">
                <span class="page-link" aria-label="Next" v-on:click="nextpage">
                  <span aria-hidden="true">&raquo;</span>
                  <span class="sr-only">Next</span>
                </span>
        </li>
    </ul>
</nav>