(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["2d0b25b9"],{2489:function(e,t,n){"use strict";n.r(t);var o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("q-page",[e._v("\n  "+e._s(e.$t("logout.processing"))+"\n")])},u=[],s=n("8847"),c={name:"PageLogout",data:function(){return{}},created:function(){var e=this;e.$store.dispatch("sMember/logout").then(function(){e.$q.notify({timeout:500,color:"positive",message:Object(s["a"])("logout.succeeded")}),e.$router.push("/")})}},r=c,a=n("2877"),i=Object(a["a"])(r,o,u,!1,null,null,null);t["default"]=i.exports}}]);