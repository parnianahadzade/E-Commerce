import{j as e}from"./index-C88s8bSQ.js";import{C as v,a as I,b as x,c as z,d as F,p as k}from"./0a099b45d73a6607595ec7f1e39c5d3f1a08a2e6_1620035268-izrun3_6.js";import{T as D,N as P}from"./NavBar-CNJfXYLX.js";import{M as U}from"./MobileFooter-D7dY_Xl2.js";import"./Input-B77FTipe.js";const W="/assets/4fb1ba5a6b5d981ce357ddf1db20048ba2cd1587_1692516449-ClI6BT0Z.webp",H="/assets/Amazings-BSIue2eX.svg",Q={active:!0,breakpoints:{},delay:4e3,jump:!1,playOnInit:!0,stopOnFocusIn:!0,stopOnInteraction:!0,stopOnMouseEnter:!1,stopOnLastSnap:!1,rootNode:null};function g(c={}){let t,s,d,o=!1,i=!0,f=!1,u=0;function C(n,p){s=n;const{mergeOptions:m,optionsAtMedia:b}=p,_=m(Q,g.globalOptions),M=m(_,c);if(t=b(M),s.scrollSnapList().length<=1)return;f=t.jump,d=!1;const{eventStore:r,ownerDocument:L}=s.internalEngine(),N=s.rootNode(),y=t.rootNode&&t.rootNode(N)||N,w=s.containerNode();s.on("pointerDown",a),t.stopOnInteraction||s.on("pointerUp",l),t.stopOnMouseEnter&&(r.add(y,"mouseenter",()=>{i=!1,a()}),t.stopOnInteraction||r.add(y,"mouseleave",()=>{i=!0,l()})),t.stopOnFocusIn&&(r.add(w,"focusin",a),t.stopOnInteraction||r.add(w,"focusout",l)),r.add(L,"visibilitychange",E),t.playOnInit&&!h()&&l()}function O(){s.off("pointerDown",a).off("pointerUp",l),a(),d=!0,o=!1}function l(){if(d||!i)return;o||s.emit("autoplay:play");const{ownerWindow:n}=s.internalEngine();n.clearInterval(u),u=n.setInterval(T,t.delay),o=!0}function a(){if(d)return;o&&s.emit("autoplay:stop");const{ownerWindow:n}=s.internalEngine();n.clearInterval(u),u=0,o=!1}function E(){if(h())return i=o,a();i&&l()}function h(){const{ownerDocument:n}=s.internalEngine();return n.visibilityState==="hidden"}function j(n){typeof n<"u"&&(f=n),i=!0,l()}function S(){o&&a()}function A(){o&&j()}function B(){return o}function T(){const{index:n}=s.internalEngine(),p=n.clone().add(1).get(),m=s.scrollSnapList().length-1;t.stopOnLastSnap&&p===m&&a(),s.canScrollNext()?s.scrollNext(f):s.scrollTo(0,f)}return{name:"autoplay",options:c,init:C,destroy:O,play:j,stop:S,reset:A,isPlaying:B}}g.globalOptions=void 0;const R=()=>e.jsxs(v,{plugins:[g({delay:2e3})],opts:{direction:"rtl",loop:!0},className:"w-full h-[268px] lg:h-[400px] relative ",children:[e.jsx(I,{className:"h-100",children:Array.from({length:5}).map((c,t)=>e.jsx(x,{className:"basis-[70rem] md:basis-full ",children:e.jsx("img",{className:"h-100",src:W})},t))}),e.jsx(z,{moreCss:"absolute top-1/2  right-2",shape:"rounded-full",size:"lg",bgColor:"bg-white"}),e.jsx(F,{moreCss:"absolute top-1/2  left-2",shape:"rounded-full",size:"lg",bgColor:"bg-white"})]}),J="/assets/d96e7ea767ac9c2f0e4dc0316a625a276c5716df_1716971139-C0Ark_b9.webp",V="/assets/fc62e7e61c856554dacc95025600657c94e4b260_1721815391-BQp1MWsJ.webp",X="/assets/79afc4fed1f5185d940a55bafcc4575178438d0e_1722069910-BVLez9uU.webp",Z="/assets/0838826b3d938e102a3ac44f34084e4128adcfa4_1722320454-BQfytE8B.webp",q=()=>e.jsxs("div",{className:"container max-w-screen-xl lg:mx-auto  grid lg:grid-cols-4 grid-cols-2 gap-x-4 gap-y-4 justify-center px-3",children:[e.jsx("div",{className:"flex justify-center",children:e.jsx("img",{className:" rounded-2xl",src:J})}),e.jsx("div",{className:"flex justify-center",children:e.jsx("img",{className:" rounded-2xl",src:V})}),e.jsx("div",{className:"flex justify-center",children:e.jsx("img",{className:" rounded-2xl",src:X})}),e.jsx("div",{className:"flex justify-center",children:e.jsx("img",{className:" rounded-2xl",src:Z})})]}),G=()=>e.jsx("div",{className:" mx-auto container max-w-screen-xl ",children:e.jsx(v,{opts:{direction:"rtl",dragFree:!0},className:" w-full bg-rose-500  lg:rounded-2xl  ",children:e.jsxs(I,{className:" h-100 py-4 pr-4",children:[e.jsx(x,{className:"w-[114px] h-[114px] md:w-[132px] md:h-[132px] ",children:e.jsx("div",{className:"",children:e.jsx("img",{src:H})})}),Array.from({length:10}).map((c,t)=>e.jsxs(x,{className:" p-3  flex flex-col gap-y-2 bg-white "+(t===0?" rounded-r-2xl":""),children:[e.jsx("div",{className:" w-[114px] h-[114px] md:w-[132px] md:h-[132px]",children:e.jsx("img",{src:k})}),e.jsx("div",{className:"w-[114px]  md:w-[132px] h-[42px] overflow-hidden text-xs text-slate-500 font-semibold",children:"قرص سلنیوم پلاس او پی دی فارما بسته 60 عددی"}),e.jsxs("div",{className:"flex justify-between items-center mb-3",children:[e.jsx(D,{bgColor:"bg-red-600",txtColor:"text-white",morCss:"text-sm font-basic",size:"xs",children:"۸۴٪"}),e.jsx("div",{className:" font-bold text-xs",children:"۲۳.۸۱۰ تومان"})]})]},t))]})})}),K=()=>e.jsxs("div",{className:" mt-3 w-full gap-y-4 flex flex-col ",children:[e.jsx(R,{}),e.jsx(G,{}),e.jsx(q,{})]}),oe=()=>e.jsxs("div",{className:"home-page ",children:[e.jsx(P,{}),e.jsx(U,{}),e.jsx(K,{})]});export{oe as default};
