import{j as e}from"./index-Cpwao1zq.js";import{C as v,a as I,b as x,c as D,d as F}from"./Carousel-BALhdWtF.js";import{p as k,M as P}from"./0a099b45d73a6607595ec7f1e39c5d3f1a08a2e6_1620035268-8ztwx05G.js";import{T as _,N as H}from"./Tag-CYzLlHBT.js";import"./Input-em4EWStJ.js";const R="/assets/4fb1ba5a6b5d981ce357ddf1db20048ba2cd1587_1692516449-ClI6BT0Z.webp",U={active:!0,breakpoints:{},delay:4e3,jump:!1,playOnInit:!0,stopOnFocusIn:!0,stopOnInteraction:!0,stopOnMouseEnter:!1,stopOnLastSnap:!1,rootNode:null};function g(c={}){let s,t,d,o=!1,r=!0,p=!1,u=0;function C(n,m){t=n;const{mergeOptions:f,optionsAtMedia:j}=m,L=f(U,g.globalOptions),z=f(L,c);if(s=j(z),t.scrollSnapList().length<=1)return;p=s.jump,d=!1;const{eventStore:a,ownerDocument:B}=t.internalEngine(),y=t.rootNode(),N=s.rootNode&&s.rootNode(y)||y,w=t.containerNode();t.on("pointerDown",l),s.stopOnInteraction||t.on("pointerUp",i),s.stopOnMouseEnter&&(a.add(N,"mouseenter",()=>{r=!1,l()}),s.stopOnInteraction||a.add(N,"mouseleave",()=>{r=!0,i()})),s.stopOnFocusIn&&(a.add(w,"focusin",l),s.stopOnInteraction||a.add(w,"focusout",i)),a.add(B,"visibilitychange",S),s.playOnInit&&!h()&&i()}function O(){t.off("pointerDown",l).off("pointerUp",i),l(),d=!0,o=!1}function i(){if(d||!r)return;o||t.emit("autoplay:play");const{ownerWindow:n}=t.internalEngine();n.clearInterval(u),u=n.setInterval(M,s.delay),o=!0}function l(){if(d)return;o&&t.emit("autoplay:stop");const{ownerWindow:n}=t.internalEngine();n.clearInterval(u),u=0,o=!1}function S(){if(h())return r=o,l();r&&i()}function h(){const{ownerDocument:n}=t.internalEngine();return n.visibilityState==="hidden"}function b(n){typeof n<"u"&&(p=n),r=!0,i()}function E(){o&&l()}function T(){o&&b()}function A(){return o}function M(){const{index:n}=t.internalEngine(),m=n.clone().add(1).get(),f=t.scrollSnapList().length-1;s.stopOnLastSnap&&m===f&&l(),t.canScrollNext()?t.scrollNext(p):t.scrollTo(0,p)}return{name:"autoplay",options:c,init:C,destroy:O,play:b,stop:E,reset:T,isPlaying:A}}g.globalOptions=void 0;const W=()=>e.jsxs(v,{plugins:[g({delay:2e3})],opts:{direction:"rtl",loop:!0},className:"w-full h-[268px] lg:h-[400px] relative ",children:[e.jsx(I,{className:"h-100",children:Array.from({length:5}).map((c,s)=>e.jsx(x,{className:"basis-[70rem] md:basis-full ",children:e.jsx("img",{className:"h-100",src:R})},s))}),e.jsx(D,{moreCss:"absolute top-1/2  right-2",shape:"rounded-full",size:"lg",bgColor:"bg-white"}),e.jsx(F,{moreCss:"absolute top-1/2  left-2",shape:"rounded-full",size:"lg",bgColor:"bg-white"})]}),X="/assets/Amazings-BSIue2eX.svg",Z=()=>e.jsx("div",{className:" mx-auto container max-w-screen-xl ",children:e.jsx(v,{opts:{direction:"rtl",dragFree:!0},className:" w-full bg-rose-500  lg:rounded-2xl  ",children:e.jsxs(I,{className:" h-100 py-4 pr-4",children:[e.jsx(x,{className:"w-[114px] h-[114px] md:w-[132px] md:h-[132px] ",children:e.jsx("div",{className:"",children:e.jsx("img",{src:X})})}),Array.from({length:10}).map((c,s)=>e.jsxs(x,{className:" p-3  flex flex-col gap-y-2 bg-white "+(s===0?" rounded-r-2xl":""),children:[e.jsx("div",{className:" w-[114px] h-[114px] md:w-[132px] md:h-[132px]",children:e.jsx("img",{src:k})}),e.jsx("div",{className:"w-[114px]  md:w-[132px] h-[42px] overflow-hidden text-xs text-slate-500 font-semibold",children:"قرص سلنیوم پلاس او پی دی فارما بسته 60 عددی"}),e.jsxs("div",{className:"flex justify-between items-center mb-3",children:[e.jsx(_,{bgColor:"bg-red-600",txtColor:"text-white",morCss:"text-sm font-basic",size:"xs",children:"۸۴٪"}),e.jsx("div",{className:" font-bold text-xs",children:"۲۳.۸۱۰ تومان"})]})]},s))]})})}),q=()=>e.jsxs("div",{className:" mt-3 w-full gap-y-4 flex flex-col ",children:[e.jsx(W,{}),e.jsx(Z,{})]}),$=()=>e.jsxs("div",{className:"home-page ",children:[e.jsx(H,{}),e.jsx(P,{}),e.jsx(q,{})]});export{$ as default};
