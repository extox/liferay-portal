define("frontend-js-metal-web@1.0.6/metal-rating/src/Rating.soy-min", ["exports","metal-component/src/Component","metal-soy/src/Soy"], function(e,t,n){"use strict";function o(e){return e&&e.__esModule?e:{"default":e}}function a(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function l(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0}),e.templates=e.Rating=void 0;var r,s=o(t),u=o(n);goog.loadModule(function(e){function t(e,t,n){o("div",null,null,"aria-valuemin",e.options[0].value,"aria-valuemax",e.options[e.options.length-1].value,"aria-valuenow",e.options[e.value]?e.options[e.value].value:"","aria-valuetext",e.options[e.value]?e.options[e.value].title:"","class","rating"),e.label&&(o("label",null,null,"class","rate-label"),i((goog.asserts.assert(null!=e.label),e.label)),a("label")),o("div",null,null,"class","rating-items");for(var r=e.options.length,s=0;r>s;s++)l("button",null,null,"aria-disabled",e.disabled,"aria-pressed",s<=e.value,"aria-label",e.options[s].title,"class","btn rating-item "+(s<=e.value?e.cssClasses.on:e.cssClasses.off),"data-index",s,"title",e.options[s].title,"type","button");a("div"),o("input",null,null,"type","hidden","aria-hidden","true","name",e.inputHiddenName,"value",e.options[e.value]?e.options[e.value].value:e.value),a("input"),a("div")}goog.module("Rating.incrementaldom");goog.require("soy"),goog.require("soydata");goog.require("goog.i18n.bidi"),goog.require("goog.asserts");var n=goog.require("incrementaldom"),o=n.elementOpen,a=n.elementClose,l=n.elementVoid,i=(n.elementOpenStart,n.elementOpenEnd,n.text);n.attr;return (e.render=t, goog.DEBUG&&(t.soyTemplateName="Rating.render"), e.render.params=["label","cssClasses","disabled","inputHiddenName","options","value"], e.templates=r=e, e)});var p=function(e){function t(){return (a(this,t), l(this,e.apply(this,arguments)))}return (i(t,e), t)}(s["default"]);u["default"].register(p,r),e["default"]=r,e.Rating=p,e.templates=r});