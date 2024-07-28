import{r as M,j as G}from"./index-9WWSea0y.js";import{B as Rt}from"./Input-5XraGlGX.js";function Wt(t){return Object.prototype.toString.call(t)==="[object Object]"}function jt(t){return Wt(t)||Array.isArray(t)}function tn(){return!!(typeof window<"u"&&window.document&&window.document.createElement)}function It(t,n){const o=Object.keys(t),i=Object.keys(n);if(o.length!==i.length)return!1;const c=JSON.stringify(Object.keys(t.breakpoints||{})),r=JSON.stringify(Object.keys(n.breakpoints||{}));return c!==r?!1:o.every(s=>{const u=t[s],e=n[s];return typeof u=="function"?`${u}`==`${e}`:!jt(u)||!jt(e)?u===e:It(u,e)})}function zt(t){return t.concat().sort((n,o)=>n.name>o.name?1:-1).map(n=>n.options)}function nn(t,n){if(t.length!==n.length)return!1;const o=zt(t),i=zt(n);return o.every((c,r)=>{const s=i[r];return It(c,s)})}function Lt(t){return typeof t=="number"}function vt(t){return typeof t=="string"}function Pt(t){return typeof t=="boolean"}function Bt(t){return Object.prototype.toString.call(t)==="[object Object]"}function w(t){return Math.abs(t)}function Tt(t){return Math.sign(t)}function ut(t,n){return w(t-n)}function en(t,n){if(t===0||n===0||w(t)<=w(n))return 0;const o=ut(w(t),w(n));return w(o/t)}function at(t){return lt(t).map(Number)}function q(t){return t[dt(t)]}function dt(t){return Math.max(0,t.length-1)}function wt(t,n){return n===dt(t)}function Vt(t,n=0){return Array.from(Array(t),(o,i)=>n+i)}function lt(t){return Object.keys(t)}function Ht(t,n){return[t,n].reduce((o,i)=>(lt(i).forEach(c=>{const r=o[c],s=i[c],u=Bt(r)&&Bt(s);o[c]=u?Ht(r,s):s}),o),{})}function Ct(t,n){return typeof n.MouseEvent<"u"&&t instanceof n.MouseEvent}function on(t,n){const o={start:i,center:c,end:r};function i(){return 0}function c(e){return r(e)/2}function r(e){return n-e}function s(e,a){return vt(t)?o[t](e):t(n,e,a)}return{measure:s}}function ft(){let t=[];function n(c,r,s,u={passive:!0}){let e;if("addEventListener"in c)c.addEventListener(r,s,u),e=()=>c.removeEventListener(r,s,u);else{const a=c;a.addListener(s),e=()=>a.removeListener(s)}return t.push(e),i}function o(){t=t.filter(c=>c())}const i={add:n,clear:o};return i}function rn(t,n,o,i){const c=ft(),r=1e3/60;let s=null,u=0,e=0;function a(){c.add(t,"visibilitychange",()=>{t.hidden&&d()})}function g(){x(),c.clear()}function f(y){if(!e)return;s||(s=y);const l=y-s;for(s=y,u+=l;u>=r;)o(),u-=r;const h=w(u/r);i(h),e&&n.requestAnimationFrame(f)}function m(){e||(e=n.requestAnimationFrame(f))}function x(){n.cancelAnimationFrame(e),s=null,u=0,e=0}function d(){s=null,u=0}return{init:a,destroy:g,start:m,stop:x,update:o,render:i}}function sn(t,n){const o=n==="rtl",i=t==="y",c=i?"y":"x",r=i?"x":"y",s=!i&&o?-1:1,u=g(),e=f();function a(d){const{height:p,width:y}=d;return i?p:y}function g(){return i?"top":o?"right":"left"}function f(){return i?"bottom":o?"left":"right"}function m(d){return d*s}return{scroll:c,cross:r,startEdge:u,endEdge:e,measureSize:a,direction:m}}function ot(t=0,n=0){const o=w(t-n);function i(a){return a<t}function c(a){return a>n}function r(a){return i(a)||c(a)}function s(a){return r(a)?i(a)?t:n:a}function u(a){return o?a-o*Math.ceil((a-n)/o):a}return{length:o,max:n,min:t,constrain:s,reachedAny:r,reachedMax:c,reachedMin:i,removeOffset:u}}function Gt(t,n,o){const{constrain:i}=ot(0,t),c=t+1;let r=s(n);function s(m){return o?w((c+m)%c):i(m)}function u(){return r}function e(m){return r=s(m),f}function a(m){return g().set(u()+m)}function g(){return Gt(t,u(),o)}const f={get:u,set:e,add:a,clone:g};return f}function cn(t,n,o,i,c,r,s,u,e,a,g,f,m,x,d,p,y,l,h){const{cross:b,direction:v}=t,I=["INPUT","SELECT","TEXTAREA"],D={passive:!1},E=ft(),C=ft(),P=ot(50,225).constrain(x.measure(20)),T={mouse:300,touch:400},O={mouse:500,touch:600},j=d?43:25;let _=!1,B=0,U=0,W=!1,Q=!1,Z=!1,J=!1;function tt(S){if(!h)return;function L(k){(Pt(h)||h(S,k))&&z(k)}const A=n;E.add(A,"dragstart",k=>k.preventDefault(),D).add(A,"touchmove",()=>{},D).add(A,"touchend",()=>{}).add(A,"touchstart",L).add(A,"mousedown",L).add(A,"touchcancel",F).add(A,"contextmenu",F).add(A,"click",st,!0)}function X(){E.clear(),C.clear()}function rt(){const S=J?o:n;C.add(S,"touchmove",$,D).add(S,"touchend",F).add(S,"mousemove",$,D).add(S,"mouseup",F)}function Y(S){const L=S.nodeName||"";return I.includes(L)}function it(){return(d?O:T)[J?"mouse":"touch"]}function ct(S,L){const A=f.add(Tt(S)*-1),k=g.byDistance(S,!d).distance;return d||w(S)<P?k:y&&L?k*.5:g.byIndex(A.get(),0).distance}function z(S){const L=Ct(S,i);J=L,Z=d&&L&&!S.buttons&&_,_=ut(c.get(),s.get())>=2,!(L&&S.button!==0)&&(Y(S.target)||(W=!0,r.pointerDown(S),a.useFriction(0).useDuration(0),c.set(s),rt(),B=r.readPoint(S),U=r.readPoint(S,b),m.emit("pointerDown")))}function $(S){if(!Ct(S,i)&&S.touches.length>=2)return F(S);const A=r.readPoint(S),k=r.readPoint(S,b),K=ut(A,B),R=ut(k,U);if(!Q&&!J&&(!S.cancelable||(Q=K>R,!Q)))return F(S);const H=r.pointerMove(S);K>p&&(Z=!0),a.useFriction(.3).useDuration(.75),u.start(),c.add(v(H)),S.preventDefault()}function F(S){const A=g.byDistance(0,!1).index!==f.get(),k=r.pointerUp(S)*it(),K=ct(v(k),A),R=en(k,K),H=j-10*R,nt=l+R/50;Q=!1,W=!1,C.clear(),a.useDuration(H).useFriction(nt),e.distance(K,!d),J=!1,m.emit("pointerUp")}function st(S){Z&&(S.stopPropagation(),S.preventDefault(),Z=!1)}function V(){return W}return{init:tt,destroy:X,pointerDown:V}}function un(t,n){let i,c;function r(f){return f.timeStamp}function s(f,m){const d=`client${(m||t.scroll)==="x"?"X":"Y"}`;return(Ct(f,n)?f:f.touches[0])[d]}function u(f){return i=f,c=f,s(f)}function e(f){const m=s(f)-s(c),x=r(f)-r(i)>170;return c=f,x&&(i=f),m}function a(f){if(!i||!c)return 0;const m=s(c)-s(i),x=r(f)-r(i),d=r(f)-r(c)>170,p=m/x;return x&&!d&&w(p)>.1?p:0}return{pointerDown:u,pointerMove:e,pointerUp:a,readPoint:s}}function an(){function t(o){const{offsetTop:i,offsetLeft:c,offsetWidth:r,offsetHeight:s}=o;return{top:i,right:c+r,bottom:i+s,left:c,width:r,height:s}}return{measure:t}}function ln(t){function n(i){return t*(i/100)}return{measure:n}}function fn(t,n,o,i,c,r,s){let u,e,a=[],g=!1;function f(p){return c.measureSize(s.measure(p))}function m(p){if(!r)return;e=f(t),a=i.map(f);function y(h){for(const b of h){const v=b.target===t,I=i.indexOf(b.target),D=v?e:a[I],E=f(v?t:i[I]);if(w(E-D)>=.5){o.requestAnimationFrame(()=>{p.reInit(),n.emit("resize")});break}}}u=new ResizeObserver(h=>{g||(Pt(r)||r(p,h))&&y(h)}),[t].concat(i).forEach(h=>u.observe(h))}function x(){u&&u.disconnect(),g=!0}return{init:m,destroy:x}}function dn(t,n,o,i,c){let r=0,s=0,u=i,e=c,a=t.get(),g=0;function f(){const I=o.get()-t.get(),D=!u;let E=0;return D?(r=0,t.set(o),E=I):(r+=I/u,r*=e,a+=r,t.add(r),E=a-g),s=Tt(E),g=a,v}function m(){const I=o.get()-n.get();return w(I)<.001}function x(){return u}function d(){return s}function p(){return r}function y(){return h(i)}function l(){return b(c)}function h(I){return u=I,v}function b(I){return e=I,v}const v={direction:d,duration:x,velocity:p,seek:f,settled:m,useBaseFriction:l,useBaseDuration:y,useFriction:b,useDuration:h};return v}function pn(t,n,o,i,c){const r=c.measure(10),s=c.measure(50),u=ot(.1,.99);let e=!1;function a(){return!(e||!t.reachedAny(o.get())||!t.reachedAny(n.get()))}function g(x){if(!a())return;const d=t.reachedMin(n.get())?"min":"max",p=w(t[d]-n.get()),y=o.get()-n.get(),l=u.constrain(p/s);o.subtract(y*l),!x&&w(y)<r&&(o.set(t.constrain(o.get())),i.useDuration(25).useBaseFriction())}function f(x){e=!x}return{constrain:g,toggleActive:f}}function mn(t,n,o,i,c){const r=ot(-n+t,0),s=f(),u=g(),e=m();function a(d,p){return ut(d,p)<1}function g(){const d=s[0],p=q(s),y=s.lastIndexOf(d),l=s.indexOf(p)+1;return ot(y,l)}function f(){return o.map((d,p)=>{const{min:y,max:l}=r,h=r.constrain(d),b=!p,v=wt(o,p);return b?l:v||a(y,h)?y:a(l,h)?l:h}).map(d=>parseFloat(d.toFixed(3)))}function m(){if(n<=t+c)return[r.max];if(i==="keepSnaps")return s;const{min:d,max:p}=u;return s.slice(d,p)}return{snapsContained:e,scrollContainLimit:u}}function gn(t,n,o){const i=n[0],c=o?i-t:q(n);return{limit:ot(c,i)}}function hn(t,n,o,i){const r=n.min+.1,s=n.max+.1,{reachedMin:u,reachedMax:e}=ot(r,s);function a(m){return m===1?e(o.get()):m===-1?u(o.get()):!1}function g(m){if(!a(m))return;const x=t*(m*-1);i.forEach(d=>d.add(x))}return{loop:g}}function yn(t){const{max:n,length:o}=t;function i(r){const s=r-n;return o?s/-o:0}return{get:i}}function Sn(t,n,o,i,c){const{startEdge:r,endEdge:s}=t,{groupSlides:u}=c,e=f().map(n.measure),a=m(),g=x();function f(){return u(i).map(p=>q(p)[s]-p[0][r]).map(w)}function m(){return i.map(p=>o[r]-p[r]).map(p=>-w(p))}function x(){return u(a).map(p=>p[0]).map((p,y)=>p+e[y])}return{snaps:a,snapsAligned:g}}function xn(t,n,o,i,c,r){const{groupSlides:s}=c,{min:u,max:e}=i,a=g();function g(){const m=s(r),x=!t||n==="keepSnaps";return o.length===1?[r]:x?m:m.slice(u,e).map((d,p,y)=>{const l=!p,h=wt(y,p);if(l){const b=q(y[0])+1;return Vt(b)}if(h){const b=dt(r)-q(y)[0]+1;return Vt(b,q(y)[0])}return d})}return{slideRegistry:a}}function bn(t,n,o,i,c){const{reachedAny:r,removeOffset:s,constrain:u}=i;function e(d){return d.concat().sort((p,y)=>w(p)-w(y))[0]}function a(d){const p=t?s(d):u(d),y=n.map((h,b)=>({diff:g(h-p,0),index:b})).sort((h,b)=>w(h.diff)-w(b.diff)),{index:l}=y[0];return{index:l,distance:p}}function g(d,p){const y=[d,d+o,d-o];if(!t)return d;if(!p)return e(y);const l=y.filter(h=>Tt(h)===p);return l.length?e(l):q(y)-o}function f(d,p){const y=n[d]-c.get(),l=g(y,p);return{index:d,distance:l}}function m(d,p){const y=c.get()+d,{index:l,distance:h}=a(y),b=!t&&r(y);if(!p||b)return{index:l,distance:d};const v=n[l]-h,I=d+g(v,0);return{index:l,distance:I}}return{byDistance:m,byIndex:f,shortcut:g}}function En(t,n,o,i,c,r,s){function u(f){const m=f.distance,x=f.index!==n.get();r.add(m),m&&(i.duration()?t.start():(t.update(),t.render(1),t.update())),x&&(o.set(n.get()),n.set(f.index),s.emit("select"))}function e(f,m){const x=c.byDistance(f,m);u(x)}function a(f,m){const x=n.clone().set(f),d=c.byIndex(x.get(),m);u(d)}return{distance:e,index:a}}function vn(t,n,o,i,c,r,s){let u=0;function e(){r.add(document,"keydown",a,!1),n.forEach(g)}function a(m){m.code==="Tab"&&(u=new Date().getTime())}function g(m){const x=()=>{if(new Date().getTime()-u>10)return;t.scrollLeft=0;const y=n.indexOf(m),l=o.findIndex(h=>h.includes(y));Lt(l)&&(c.useDuration(0),i.index(l,0),s.emit("slideFocus"))};r.add(m,"focus",x,{passive:!0,capture:!0})}return{init:e}}function mt(t){let n=t;function o(){return n}function i(e){n=s(e)}function c(e){n+=s(e)}function r(e){n-=s(e)}function s(e){return Lt(e)?e:e.get()}return{get:o,set:i,add:c,subtract:r}}function qt(t,n){const o=t.scroll==="x"?r:s,i=n.style;let c=!1;function r(f){return`translate3d(${f}px,0px,0px)`}function s(f){return`translate3d(0px,${f}px,0px)`}function u(f){c||(i.transform=o(t.direction(f)))}function e(f){c=!f}function a(){c||(i.transform="",n.getAttribute("style")||n.removeAttribute("style"))}return{clear:a,to:u,toggleActive:e}}function Cn(t,n,o,i,c,r,s,u,e){const g=at(c),f=at(c).reverse(),m=l().concat(h());function x(E,C){return E.reduce((P,T)=>P-c[T],C)}function d(E,C){return E.reduce((P,T)=>x(P,C)>0?P.concat([T]):P,[])}function p(E){return r.map((C,P)=>({start:C-i[P]+.5+E,end:C+n-.5+E}))}function y(E,C,P){const T=p(C);return E.map(O=>{const j=P?0:-o,_=P?o:0,B=P?"end":"start",U=T[O][B];return{index:O,loopPoint:U,slideLocation:mt(-1),translate:qt(t,e[O]),target:()=>u.get()>U?j:_}})}function l(){const E=s[0],C=d(f,E);return y(C,o,!1)}function h(){const E=n-s[0]-1,C=d(g,E);return y(C,-o,!0)}function b(){return m.every(({index:E})=>{const C=g.filter(P=>P!==E);return x(C,n)<=.1})}function v(){m.forEach(E=>{const{target:C,translate:P,slideLocation:T}=E,O=C();O!==T.get()&&(P.to(O),T.set(O))})}function I(){m.forEach(E=>E.translate.clear())}return{canLoop:b,clear:I,loop:v,loopPoints:m}}function In(t,n,o){let i,c=!1;function r(e){if(!o)return;function a(g){for(const f of g)if(f.type==="childList"){e.reInit(),n.emit("slidesChanged");break}}i=new MutationObserver(g=>{c||(Pt(o)||o(e,g))&&a(g)}),i.observe(t,{childList:!0})}function s(){i&&i.disconnect(),c=!0}return{init:r,destroy:s}}function Ln(t,n,o,i){const c={};let r=null,s=null,u,e=!1;function a(){u=new IntersectionObserver(d=>{e||(d.forEach(p=>{const y=n.indexOf(p.target);c[y]=p}),r=null,s=null,o.emit("slidesInView"))},{root:t.parentElement,threshold:i}),n.forEach(d=>u.observe(d))}function g(){u&&u.disconnect(),e=!0}function f(d){return lt(c).reduce((p,y)=>{const l=parseInt(y),{isIntersecting:h}=c[l];return(d&&h||!d&&!h)&&p.push(l),p},[])}function m(d=!0){if(d&&r)return r;if(!d&&s)return s;const p=f(d);return d&&(r=p),d||(s=p),p}return{init:a,destroy:g,get:m}}function Pn(t,n,o,i,c,r){const{measureSize:s,startEdge:u,endEdge:e}=t,a=o[0]&&c,g=d(),f=p(),m=o.map(s),x=y();function d(){if(!a)return 0;const h=o[0];return w(n[u]-h[u])}function p(){if(!a)return 0;const h=r.getComputedStyle(q(i));return parseFloat(h.getPropertyValue(`margin-${e}`))}function y(){return o.map((h,b,v)=>{const I=!b,D=wt(v,b);return I?m[b]+g:D?m[b]+f:v[b+1][u]-h[u]}).map(w)}return{slideSizes:m,slideSizesWithGaps:x,startGap:g,endGap:f}}function Tn(t,n,o,i,c,r,s,u,e){const{startEdge:a,endEdge:g,direction:f}=t,m=Lt(o);function x(l,h){return at(l).filter(b=>b%h===0).map(b=>l.slice(b,b+h))}function d(l){return l.length?at(l).reduce((h,b,v)=>{const I=q(h)||0,D=I===0,E=b===dt(l),C=c[a]-r[I][a],P=c[a]-r[b][g],T=!i&&D?f(s):0,O=!i&&E?f(u):0,j=w(P-O-(C+T));return v&&j>n+e&&h.push(b),E&&h.push(l.length),h},[]).map((h,b,v)=>{const I=Math.max(v[b-1]||0);return l.slice(I,h)}):[]}function p(l){return m?x(l,o):d(l)}return{groupSlides:p}}function wn(t,n,o,i,c,r,s){const{align:u,axis:e,direction:a,startIndex:g,loop:f,duration:m,dragFree:x,dragThreshold:d,inViewThreshold:p,slidesToScroll:y,skipSnaps:l,containScroll:h,watchResize:b,watchSlides:v,watchDrag:I}=r,D=2,E=an(),C=E.measure(n),P=o.map(E.measure),T=sn(e,a),O=T.measureSize(C),j=ln(O),_=on(u,O),B=!f&&!!h,U=f||!!h,{slideSizes:W,slideSizesWithGaps:Q,startGap:Z,endGap:J}=Pn(T,C,P,o,U,c),tt=Tn(T,O,y,f,C,P,Z,J,D),{snaps:X,snapsAligned:rt}=Sn(T,_,C,P,tt),Y=-q(X)+q(Q),{snapsContained:it,scrollContainLimit:ct}=mn(O,Y,rt,h,D),z=B?it:rt,{limit:$}=gn(Y,z,f),F=Gt(dt(z),g,f),st=F.clone(),V=at(o),N=({dragHandler:et,scrollBody:bt,scrollBounds:Et,options:{loop:pt}})=>{pt||Et.constrain(et.pointerDown()),bt.seek()},S=({scrollBody:et,translate:bt,location:Et,offsetLocation:pt,scrollLooper:Qt,slideLooper:Jt,dragHandler:Xt,animation:Yt,eventHandler:Mt,options:{loop:_t}},Zt)=>{const kt=et.velocity(),Ft=et.settled();Ft&&!Xt.pointerDown()&&(Yt.stop(),Mt.emit("settle")),Ft||Mt.emit("scroll"),pt.set(Et.get()-kt+kt*Zt),_t&&(Qt.loop(et.direction()),Jt.loop()),bt.to(pt.get())},L=rn(i,c,()=>N(xt),et=>S(xt,et)),A=.68,k=z[F.get()],K=mt(k),R=mt(k),H=mt(k),nt=dn(K,R,H,m,A),yt=bn(f,z,Y,$,H),St=En(L,F,st,nt,yt,H,s),Nt=yn($),Ot=ft(),Kt=Ln(n,o,s,p),{slideRegistry:At}=xn(B,h,z,ct,tt,V),Ut=vn(t,o,At,St,nt,Ot,s),xt={ownerDocument:i,ownerWindow:c,eventHandler:s,containerRect:C,slideRects:P,animation:L,axis:T,dragHandler:cn(T,t,i,c,H,un(T,c),K,L,St,nt,yt,F,s,j,x,d,l,A,I),eventStore:Ot,percentOfView:j,index:F,indexPrevious:st,limit:$,location:K,offsetLocation:R,options:r,resizeHandler:fn(n,s,c,o,T,b,E),scrollBody:nt,scrollBounds:pn($,R,H,nt,j),scrollLooper:hn(Y,$,R,[K,R,H]),scrollProgress:Nt,scrollSnapList:z.map(Nt.get),scrollSnaps:z,scrollTarget:yt,scrollTo:St,slideLooper:Cn(T,O,Y,W,Q,X,z,R,o),slideFocus:Ut,slidesHandler:In(n,s,v),slidesInView:Kt,slideIndexes:V,slideRegistry:At,slidesToScroll:tt,target:H,translate:qt(T,n)};return xt}function Dn(){let t={},n;function o(a){n=a}function i(a){return t[a]||[]}function c(a){return i(a).forEach(g=>g(n,a)),e}function r(a,g){return t[a]=i(a).concat([g]),e}function s(a,g){return t[a]=i(a).filter(f=>f!==g),e}function u(){t={}}const e={init:o,emit:c,off:s,on:r,clear:u};return e}const Nn={align:"center",axis:"x",container:null,slides:null,containScroll:"trimSnaps",direction:"ltr",slidesToScroll:1,inViewThreshold:0,breakpoints:{},dragFree:!1,dragThreshold:10,loop:!1,skipSnaps:!1,duration:25,startIndex:0,active:!0,watchDrag:!0,watchResize:!0,watchSlides:!0};function On(t){function n(r,s){return Ht(r,s||{})}function o(r){const s=r.breakpoints||{},u=lt(s).filter(e=>t.matchMedia(e).matches).map(e=>s[e]).reduce((e,a)=>n(e,a),{});return n(r,u)}function i(r){return r.map(s=>lt(s.breakpoints||{})).reduce((s,u)=>s.concat(u),[]).map(t.matchMedia)}return{mergeOptions:n,optionsAtMedia:o,optionsMediaQueries:i}}function An(t){let n=[];function o(r,s){return n=s.filter(({options:u})=>t.optionsAtMedia(u).active!==!1),n.forEach(u=>u.init(r,t)),s.reduce((u,e)=>Object.assign(u,{[e.name]:e}),{})}function i(){n=n.filter(r=>r.destroy())}return{init:o,destroy:i}}function gt(t,n,o){const i=t.ownerDocument,c=i.defaultView,r=On(c),s=An(r),u=ft(),e=Dn(),{mergeOptions:a,optionsAtMedia:g,optionsMediaQueries:f}=r,{on:m,off:x,emit:d}=e,p=O;let y=!1,l,h=a(Nn,gt.globalOptions),b=a(h),v=[],I,D,E;function C(){const{container:N,slides:S}=b;D=(vt(N)?t.querySelector(N):N)||t.children[0];const A=vt(S)?D.querySelectorAll(S):S;E=[].slice.call(A||D.children)}function P(N){const S=wn(t,D,E,i,c,N,e);if(N.loop&&!S.slideLooper.canLoop()){const L=Object.assign({},N,{loop:!1});return P(L)}return S}function T(N,S){y||(h=a(h,N),b=g(h),v=S||v,C(),l=P(b),f([h,...v.map(({options:L})=>L)]).forEach(L=>u.add(L,"change",O)),b.active&&(l.translate.to(l.location.get()),l.animation.init(),l.slidesInView.init(),l.slideFocus.init(),l.eventHandler.init(V),l.resizeHandler.init(V),l.slidesHandler.init(V),l.options.loop&&l.slideLooper.loop(),D.offsetParent&&E.length&&l.dragHandler.init(V),I=s.init(V,v)))}function O(N,S){const L=X();j(),T(a({startIndex:L},N),S),e.emit("reInit")}function j(){l.dragHandler.destroy(),l.eventStore.clear(),l.translate.clear(),l.slideLooper.clear(),l.resizeHandler.destroy(),l.slidesHandler.destroy(),l.slidesInView.destroy(),l.animation.destroy(),s.destroy(),u.clear()}function _(){y||(y=!0,u.clear(),j(),e.emit("destroy"),e.clear())}function B(N,S,L){!b.active||y||(l.scrollBody.useBaseFriction().useDuration(S===!0?0:b.duration),l.scrollTo.index(N,L||0))}function U(N){const S=l.index.add(1).get();B(S,N,-1)}function W(N){const S=l.index.add(-1).get();B(S,N,1)}function Q(){return l.index.add(1).get()!==X()}function Z(){return l.index.add(-1).get()!==X()}function J(){return l.scrollSnapList}function tt(){return l.scrollProgress.get(l.location.get())}function X(){return l.index.get()}function rt(){return l.indexPrevious.get()}function Y(){return l.slidesInView.get()}function it(){return l.slidesInView.get(!1)}function ct(){return I}function z(){return l}function $(){return t}function F(){return D}function st(){return E}const V={canScrollNext:Q,canScrollPrev:Z,containerNode:F,internalEngine:z,destroy:_,off:x,on:m,emit:d,plugins:ct,previousScrollSnap:rt,reInit:p,rootNode:$,scrollNext:U,scrollPrev:W,scrollProgress:tt,scrollSnapList:J,scrollTo:B,selectedScrollSnap:X,slideNodes:st,slidesInView:Y,slidesNotInView:it};return T(n,o),setTimeout(()=>e.emit("init"),0),V}gt.globalOptions=void 0;function Dt(t={},n=[]){const o=M.useRef(t),i=M.useRef(n),[c,r]=M.useState(),[s,u]=M.useState(),e=M.useCallback(()=>{c&&c.reInit(o.current,i.current)},[c]);return M.useEffect(()=>{if(tn()&&s){gt.globalOptions=Dt.globalOptions;const a=gt(s,o.current,i.current);return r(a),()=>a.destroy()}else r(void 0)},[s,r]),M.useEffect(()=>{It(o.current,t)||(o.current=t,e())},[t,e]),M.useEffect(()=>{nn(i.current,n)||(i.current=n,e())},[n,e]),[u,c]}Dt.globalOptions=void 0;const $t=M.createContext(null);function ht(){const t=M.useContext($t);if(!t)throw new Error("useCarousel must be used within a <Carousel />");return t}const Mn=({orientation:t="horizontal",opts:n,setApi:o,plugins:i,className:c,children:r,...s})=>{const[u,e]=Dt({...n,axis:t==="horizontal"?"x":"y"},i),[a,g]=M.useState(!1),[f,m]=M.useState(!1),x=M.useCallback(l=>{l&&(g(l.canScrollPrev()),m(l.canScrollNext()))},[]),d=M.useCallback(()=>{e==null||e.scrollPrev()},[e]),p=M.useCallback(()=>{e==null||e.scrollNext()},[e]),y=M.useCallback(l=>{l.key==="ArrowLeft"?(l.preventDefault(),d()):l.key==="ArrowRight"&&(l.preventDefault(),p())},[d,p]);return M.useEffect(()=>{!e||!o||o(e)},[e,o]),M.useEffect(()=>{if(e)return x(e),e.on("reInit",x),e.on("select",x),()=>{e==null||e.off("select",x)}},[e,x]),G.jsx($t.Provider,{value:{carouselRef:u,api:e,opts:n,orientation:t||((n==null?void 0:n.axis)==="y"?"vertical":"horizontal"),scrollPrev:d,scrollNext:p,canScrollPrev:a,canScrollNext:f},children:G.jsx("div",{onKeyDownCapture:y,className:"relative "+c,role:"region","aria-roledescription":"carousel",...s,children:r})})};Mn.displayName="Carousel";const kn=({className:t,...n})=>{const{carouselRef:o,orientation:i}=ht();return G.jsx("div",{ref:o,className:"overflow-hidden h-100",children:G.jsx("div",{className:"flex "+(i==="horizontal"?"-ml-4":"-mt-4 flex-col")+" "+t,...n})})};kn.displayName="CarouselContent";const Fn=({className:t,...n})=>{const{orientation:o}=ht();return G.jsx("div",{role:"group","aria-roledescription":"slide",className:"min-w-0 shrink-0 grow-0  "+(o==="horizontal"?"ml-1":"mt-1")+" "+t,...n})};Fn.displayName="CarouselItem";const jn=({moreCss:t,shape:n,size:o,bgColor:i,txtColor:c,...r})=>{const{orientation:s,scrollPrev:u,canScrollPrev:e}=ht();return G.jsxs(Rt,{bgColor:i,txtColor:c,shape:n,size:o,moreCss:t,disabled:!e,onClick:u,...r,children:[G.jsx("i",{class:"fa-solid  fa-angle-right w-4 h-4"}),G.jsx("span",{className:"sr-only",children:"Next slide"})]})};jn.displayName="CarouselPrevious";const zn=({shape:t,moreCss:n,size:o,bgColor:i,txtColor:c,...r})=>{const{orientation:s,scrollNext:u,canScrollNext:e}=ht();return G.jsxs(Rt,{bgColor:i,txtColor:c,shape:t,size:o,moreCss:n,disabled:!e,onClick:u,...r,children:[G.jsx("i",{class:"fa-solid fa-angle-left w-4 h-4"}),G.jsx("span",{className:"sr-only",children:"Previous slide"})]})};zn.displayName="CarouselNext";export{Mn as C,kn as a,Fn as b,jn as c,zn as d};