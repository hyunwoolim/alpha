(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["47609741"],{b06b:function(t,e,o){"use strict";var r=o("0967"),n=o("2b0e");e["a"]=function(t,e){var o=window.open;if(!0===r["b"].is.cordova){if(void 0!==cordova&&void 0!==cordova.InAppBrowser&&void 0!==cordova.InAppBrowser.open)o=cordova.InAppBrowser.open;else if(void 0!==navigator&&void 0!==navigator.app)return navigator.app.loadUrl(t,{openExternal:!0})}else if(void 0!==n["a"].prototype.$q.electron)return n["a"].prototype.$q.electron.shell.openExternal(t);var a=o(t,"_blank");if(a)return a.focus(),a;e&&e()}},f6b42:function(t,e,o){"use strict";o.r(e);var r=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("q-layout",{attrs:{view:"lHh Lpr lFf"}},[o("q-header",{attrs:{elevated:""}},[o("q-toolbar",[o("q-toolbar-title",{staticClass:"absolute-center"},[t._v("\n        admin\n      ")]),o("div")],1)],1),o("q-page-container",[o("router-view")],1)],1)},n=[],a=(o("6762"),o("2fdb"),o("b06b")),i={name:"MyLayout",data:function(){return{leftDrawerOpen:!1}},beforeRouteUpdate:function(t,e,o){t.meta&&t.meta.authorities&&t.meta.authorities.length>0?this.$store.state.sMember.isAuthenticated?(t.meta.authorities.includes(this.$store.state.sMember.authority)||"ADMIN"===this.$store.state.sMember.authority)&&o():this.$router.push("/login"):o()},methods:{openURL:a["a"],goPageMyInfo:function(){this.$router.push("/my-info")},goPageIndex:function(){this.$router.push("/")},goPageLogin:function(){this.$router.push("/login")},goPageLogout:function(){this.$router.push("/logout")}}},s=i,u=o("2877"),l=Object(u["a"])(s,r,n,!1,null,null,null);e["default"]=l.exports}}]);