import{r as l,e as q,j as s,b as ke,d as se,_ as X,R as C,f as De,s as $e}from"./index-BnAxZSSR.js";import{M as Me}from"./MobileFooter-Bk8iQ4vh.js";import{N as Le}from"./NavBar-BEa8RmYk.js";import{a as Ae,$ as Oe,b as Ie,d as Be,s as ee}from"./productApi-mcUPHWuv.js";import{p as ze,T as Ye}from"./0a099b45d73a6607595ec7f1e39c5d3f1a08a2e6_1620035268-BLSIGcyT.js";import"./Input-BQiizy7U.js";const He=({category:e})=>{const[n,r]=l.useState(!1),[t,o]=q(),i=()=>{r(n===!1)};return s.jsxs(s.Fragment,{children:[s.jsxs("li",{className:"flex items-center cursor-pointer",children:[e.childCategories.length!==0?s.jsx("i",{onClick:i,"data-active":n,className:" duration-200 fa-solid fa-angle-left px-2 data-[active=true]:-rotate-90"}):"",s.jsxs("div",{onClick:()=>o(a=>(a.set("categoryId",e.id),a)),className:"grow flex justify-between items-center",children:[s.jsxs("span",{children:[" ",e.name]}),t.get("category")===String(e.id)?s.jsx("i",{class:"fa-solid fa-check text-sky-400"}):""]})]}),s.jsx("li",{children:s.jsx(ie,{categories:e.childCategories,active:n})})]})},ie=({categories:e,active:n,className:r})=>s.jsx("ul",{"data-active":n,className:"mr-2 flex gap-y-3 flex-col text-base font-semibold data-[active=false]:h-0 overflow-hidden duration-200 ",children:e==null?void 0:e.map(t=>s.jsx(He,{active:!1,category:t}))});function Ve(e){const n=l.useRef({value:e,previous:e});return l.useMemo(()=>(n.current.value!==e&&(n.current.previous=n.current.value,n.current.value=e),n.current.previous),[e])}function Fe(e){const[n,r]=l.useState(void 0);return Ae(()=>{if(e){r({width:e.offsetWidth,height:e.offsetHeight});const t=new ResizeObserver(o=>{if(!Array.isArray(o)||!o.length)return;const i=o[0];let a,c;if("borderBoxSize"in i){const d=i.borderBoxSize,f=Array.isArray(d)?d[0]:d;a=f.inlineSize,c=f.blockSize}else a=e.offsetWidth,c=e.offsetHeight;r({width:a,height:c})});return t.observe(e,{box:"border-box"}),()=>t.unobserve(e)}else r(void 0)},[e]),n}const ae="Switch",[Ke,Ft]=Oe(ae),[We,Ue]=Ke(ae),qe=l.forwardRef((e,n)=>{const{__scopeSwitch:r,name:t,checked:o,defaultChecked:i,required:a,disabled:c,value:d="on",onCheckedChange:f,...v}=e,[u,h]=l.useState(null),x=ke(n,w=>h(w)),p=l.useRef(!1),b=u?!!u.closest("form"):!0,[m=!1,S]=Ie({prop:o,defaultProp:i,onChange:f});return l.createElement(We,{scope:r,checked:m,disabled:c},l.createElement(se.button,X({type:"button",role:"switch","aria-checked":m,"aria-required":a,"data-state":le(m),"data-disabled":c?"":void 0,disabled:c,value:d},v,{ref:x,onClick:Be(e.onClick,w=>{S(g=>!g),b&&(p.current=w.isPropagationStopped(),p.current||w.stopPropagation())})})),b&&l.createElement(Ze,{control:u,bubbles:!p.current,name:t,value:d,checked:m,required:a,disabled:c,style:{transform:"translateX(-100%)"}}))}),Xe="SwitchThumb",Ge=l.forwardRef((e,n)=>{const{__scopeSwitch:r,...t}=e,o=Ue(Xe,r);return l.createElement(se.span,X({"data-state":le(o.checked),"data-disabled":o.disabled?"":void 0},t,{ref:n}))}),Ze=e=>{const{control:n,checked:r,bubbles:t=!0,...o}=e,i=l.useRef(null),a=Ve(r),c=Fe(n);return l.useEffect(()=>{const d=i.current,f=window.HTMLInputElement.prototype,u=Object.getOwnPropertyDescriptor(f,"checked").set;if(a!==r&&u){const h=new Event("click",{bubbles:t});u.call(d,r),d.dispatchEvent(h)}},[a,r,t]),l.createElement("input",X({type:"checkbox","aria-hidden":!0,defaultChecked:r},o,{tabIndex:-1,ref:i,style:{...e.style,...c,position:"absolute",pointerEvents:"none",opacity:0,margin:0}}))};function le(e){return e?"checked":"unchecked"}const ce=qe,Je=Ge,de=({className:e,thumbclassName:n,...r})=>s.jsx(ce,{className:"  peer inline-flex h-5 w-11 shrink-0 cursor-pointer items-center rounded-full border-2 border-slate-300  transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 focus-visible:ring-offset-background disabled:cursor-not-allowed disabled:opacity-50 bg-white data-[state=checked]:bg-sky-500 data-[state=checked]:border-sky-500 "+e,...r,children:s.jsx(Je,{className:"  flex-row-reverse pointer-events-none block h-3 w-3 rounded-full bg-background shadow-lg ring-0 transition-transform data-[state=checked]:-translate-x-6 data-[state=unchecked]:-translate-x-1 data-[state=unchecked]:bg-slate-400 data-[state=checked]:bg-white  "+n})});de.displayName=ce.displayName;function ue(e,[n,r]){return Math.min(r,Math.max(n,e))}function k(e,n,{checkForDefaultPrevented:r=!0}={}){return function(o){if(e==null||e(o),r===!1||!o.defaultPrevented)return n==null?void 0:n(o)}}function Qe(e,n){typeof e=="function"?e(n):e!=null&&(e.current=n)}function fe(...e){return n=>e.forEach(r=>Qe(r,n))}function N(...e){return l.useCallback(fe(...e),e)}function he(e,n=[]){let r=[];function t(i,a){const c=l.createContext(a),d=r.length;r=[...r,a];function f(u){const{scope:h,children:x,...p}=u,b=(h==null?void 0:h[e][d])||c,m=l.useMemo(()=>p,Object.values(p));return s.jsx(b.Provider,{value:m,children:x})}function v(u,h){const x=(h==null?void 0:h[e][d])||c,p=l.useContext(x);if(p)return p;if(a!==void 0)return a;throw new Error(`\`${u}\` must be used within \`${i}\``)}return f.displayName=i+"Provider",[f,v]}const o=()=>{const i=r.map(a=>l.createContext(a));return function(c){const d=(c==null?void 0:c[e])||i;return l.useMemo(()=>({[`__scope${e}`]:{...c,[e]:d}}),[c,d])}};return o.scopeName=e,[t,et(o,...n)]}function et(...e){const n=e[0];if(e.length===1)return n;const r=()=>{const t=e.map(o=>({useScope:o(),scopeName:o.scopeName}));return function(i){const a=t.reduce((c,{useScope:d,scopeName:f})=>{const u=d(i)[`__scope${f}`];return{...c,...u}},{});return l.useMemo(()=>({[`__scope${n.scopeName}`]:a}),[a])}};return r.scopeName=n.scopeName,r}function me(e){const n=l.useRef(e);return l.useEffect(()=>{n.current=e}),l.useMemo(()=>(...r)=>{var t;return(t=n.current)==null?void 0:t.call(n,...r)},[])}function tt({prop:e,defaultProp:n,onChange:r=()=>{}}){const[t,o]=nt({defaultProp:n,onChange:r}),i=e!==void 0,a=i?e:t,c=me(r),d=l.useCallback(f=>{if(i){const u=typeof f=="function"?f(e):f;u!==e&&c(u)}else o(f)},[i,e,o,c]);return[a,d]}function nt({defaultProp:e,onChange:n}){const r=l.useState(e),[t]=r,o=l.useRef(t),i=me(n);return l.useEffect(()=>{o.current!==t&&(i(t),o.current=t)},[t,o,i]),r}var rt=l.createContext(void 0);function ot(e){const n=l.useContext(rt);return e||n||"ltr"}function st(e){const n=l.useRef({value:e,previous:e});return l.useMemo(()=>(n.current.value!==e&&(n.current.previous=n.current.value,n.current.value=e),n.current.previous),[e])}var it=globalThis!=null&&globalThis.document?l.useLayoutEffect:()=>{};function at(e){const[n,r]=l.useState(void 0);return it(()=>{if(e){r({width:e.offsetWidth,height:e.offsetHeight});const t=new ResizeObserver(o=>{if(!Array.isArray(o)||!o.length)return;const i=o[0];let a,c;if("borderBoxSize"in i){const d=i.borderBoxSize,f=Array.isArray(d)?d[0]:d;a=f.inlineSize,c=f.blockSize}else a=e.offsetWidth,c=e.offsetHeight;r({width:a,height:c})});return t.observe(e,{box:"border-box"}),()=>t.unobserve(e)}else r(void 0)},[e]),n}var I=l.forwardRef((e,n)=>{const{children:r,...t}=e,o=l.Children.toArray(r),i=o.find(ct);if(i){const a=i.props.children,c=o.map(d=>d===i?l.Children.count(a)>1?l.Children.only(null):l.isValidElement(a)?a.props.children:null:d);return s.jsx(V,{...t,ref:n,children:l.isValidElement(a)?l.cloneElement(a,void 0,c):null})}return s.jsx(V,{...t,ref:n,children:r})});I.displayName="Slot";var V=l.forwardRef((e,n)=>{const{children:r,...t}=e;if(l.isValidElement(r)){const o=ut(r);return l.cloneElement(r,{...dt(t,r.props),ref:n?fe(n,o):o})}return l.Children.count(r)>1?l.Children.only(null):null});V.displayName="SlotClone";var lt=({children:e})=>s.jsx(s.Fragment,{children:e});function ct(e){return l.isValidElement(e)&&e.type===lt}function dt(e,n){const r={...n};for(const t in n){const o=e[t],i=n[t];/^on[A-Z]/.test(t)?o&&i?r[t]=(...c)=>{i(...c),o(...c)}:o&&(r[t]=o):t==="style"?r[t]={...o,...i}:t==="className"&&(r[t]=[o,i].filter(Boolean).join(" "))}return{...e,...r}}function ut(e){var t,o;let n=(t=Object.getOwnPropertyDescriptor(e.props,"ref"))==null?void 0:t.get,r=n&&"isReactWarning"in n&&n.isReactWarning;return r?e.ref:(n=(o=Object.getOwnPropertyDescriptor(e,"ref"))==null?void 0:o.get,r=n&&"isReactWarning"in n&&n.isReactWarning,r?e.props.ref:e.props.ref||e.ref)}var ft=["a","button","div","form","h2","h3","img","input","label","li","nav","ol","p","span","svg","ul"],B=ft.reduce((e,n)=>{const r=l.forwardRef((t,o)=>{const{asChild:i,...a}=t,c=i?I:n;return typeof window<"u"&&(window[Symbol.for("radix-ui")]=!0),s.jsx(c,{...a,ref:o})});return r.displayName=`Primitive.${n}`,{...e,[n]:r}},{});function ht(e){const n=e+"CollectionProvider",[r,t]=he(n),[o,i]=r(n,{collectionRef:{current:null},itemMap:new Map}),a=x=>{const{scope:p,children:b}=x,m=C.useRef(null),S=C.useRef(new Map).current;return s.jsx(o,{scope:p,itemMap:S,collectionRef:m,children:b})};a.displayName=n;const c=e+"CollectionSlot",d=C.forwardRef((x,p)=>{const{scope:b,children:m}=x,S=i(c,b),w=N(p,S.collectionRef);return s.jsx(I,{ref:w,children:m})});d.displayName=c;const f=e+"CollectionItemSlot",v="data-radix-collection-item",u=C.forwardRef((x,p)=>{const{scope:b,children:m,...S}=x,w=C.useRef(null),g=N(p,w),y=i(f,b);return C.useEffect(()=>(y.itemMap.set(w,{ref:w,...S}),()=>void y.itemMap.delete(w))),s.jsx(I,{[v]:"",ref:g,children:m})});u.displayName=f;function h(x){const p=i(e+"CollectionConsumer",x);return C.useCallback(()=>{const m=p.collectionRef.current;if(!m)return[];const S=Array.from(m.querySelectorAll(`[${v}]`));return Array.from(p.itemMap.values()).sort((y,E)=>S.indexOf(y.ref.current)-S.indexOf(E.ref.current))},[p.collectionRef,p.itemMap])}return[{Provider:a,Slot:d,ItemSlot:u},h,t]}var pe=["PageUp","PageDown"],xe=["ArrowUp","ArrowDown","ArrowLeft","ArrowRight"],be={"from-left":["Home","PageDown","ArrowDown","ArrowLeft"],"from-right":["Home","PageDown","ArrowDown","ArrowRight"],"from-bottom":["Home","PageDown","ArrowDown","ArrowLeft"],"from-top":["Home","PageDown","ArrowUp","ArrowLeft"]},$="Slider",[F,mt,pt]=ht($),[ge,Kt]=he($,[pt]),[xt,z]=ge($),ve=l.forwardRef((e,n)=>{const{name:r,min:t=0,max:o=100,step:i=1,orientation:a="horizontal",disabled:c=!1,minStepsBetweenThumbs:d=0,defaultValue:f=[t],value:v,onValueChange:u=()=>{},onValueCommit:h=()=>{},inverted:x=!1,...p}=e,b=l.useRef(new Set),m=l.useRef(0),w=a==="horizontal"?bt:gt,[g=[],y]=tt({prop:v,defaultProp:f,onChange:j=>{var _;(_=[...b.current][m.current])==null||_.focus(),u(j)}}),E=l.useRef(g);function Z(j){const P=jt(g,j);M(j,P)}function Y(j){M(j,m.current)}function Te(){const j=E.current[m.current];g[m.current]!==j&&h(g)}function M(j,P,{commit:_}={commit:!1}){const J=Rt(i),H=Nt(Math.round((j-t)/i)*i+t,J),L=ue(H,[t,o]);y((T=[])=>{const R=wt(T,L,P);if(Pt(R,d*i)){m.current=R.indexOf(L);const Q=String(R)!==String(T);return Q&&_&&h(R),Q?R:T}else return T})}return s.jsx(xt,{scope:e.__scopeSlider,name:r,disabled:c,min:t,max:o,valueIndexToChangeRef:m,thumbs:b.current,values:g,orientation:a,children:s.jsx(F.Provider,{scope:e.__scopeSlider,children:s.jsx(F.Slot,{scope:e.__scopeSlider,children:s.jsx(w,{"aria-disabled":c,"data-disabled":c?"":void 0,...p,ref:n,onPointerDown:k(p.onPointerDown,()=>{c||(E.current=g)}),min:t,max:o,inverted:x,onSlideStart:c?void 0:Z,onSlideMove:c?void 0:Y,onSlideEnd:c?void 0:Te,onHomeKeyDown:()=>!c&&M(t,0,{commit:!0}),onEndKeyDown:()=>!c&&M(o,g.length-1,{commit:!0}),onStepKeyDown:({event:j,direction:P})=>{if(!c){const H=pe.includes(j.key)||j.shiftKey&&xe.includes(j.key)?10:1,L=m.current,T=g[L],R=i*H*P;M(T+R,L,{commit:!0})}}})})})})});ve.displayName=$;var[Se,we]=ge($,{startEdge:"left",endEdge:"right",size:"width",direction:1}),bt=l.forwardRef((e,n)=>{const{min:r,max:t,dir:o,inverted:i,onSlideStart:a,onSlideMove:c,onSlideEnd:d,onStepKeyDown:f,...v}=e,[u,h]=l.useState(null),x=N(n,g=>h(g)),p=l.useRef(),b=ot(o),m=b==="ltr",S=m&&!i||!m&&i;function w(g){const y=p.current||u.getBoundingClientRect(),E=[0,y.width],Y=G(E,S?[r,t]:[t,r]);return p.current=y,Y(g-y.left)}return s.jsx(Se,{scope:e.__scopeSlider,startEdge:S?"left":"right",endEdge:S?"right":"left",direction:S?1:-1,size:"width",children:s.jsx(ye,{dir:b,"data-orientation":"horizontal",...v,ref:x,style:{...v.style,"--radix-slider-thumb-transform":"translateX(-50%)"},onSlideStart:g=>{const y=w(g.clientX);a==null||a(y)},onSlideMove:g=>{const y=w(g.clientX);c==null||c(y)},onSlideEnd:()=>{p.current=void 0,d==null||d()},onStepKeyDown:g=>{const E=be[S?"from-left":"from-right"].includes(g.key);f==null||f({event:g,direction:E?-1:1})}})})}),gt=l.forwardRef((e,n)=>{const{min:r,max:t,inverted:o,onSlideStart:i,onSlideMove:a,onSlideEnd:c,onStepKeyDown:d,...f}=e,v=l.useRef(null),u=N(n,v),h=l.useRef(),x=!o;function p(b){const m=h.current||v.current.getBoundingClientRect(),S=[0,m.height],g=G(S,x?[t,r]:[r,t]);return h.current=m,g(b-m.top)}return s.jsx(Se,{scope:e.__scopeSlider,startEdge:x?"bottom":"top",endEdge:x?"top":"bottom",size:"height",direction:x?1:-1,children:s.jsx(ye,{"data-orientation":"vertical",...f,ref:u,style:{...f.style,"--radix-slider-thumb-transform":"translateY(50%)"},onSlideStart:b=>{const m=p(b.clientY);i==null||i(m)},onSlideMove:b=>{const m=p(b.clientY);a==null||a(m)},onSlideEnd:()=>{h.current=void 0,c==null||c()},onStepKeyDown:b=>{const S=be[x?"from-bottom":"from-top"].includes(b.key);d==null||d({event:b,direction:S?-1:1})}})})}),ye=l.forwardRef((e,n)=>{const{__scopeSlider:r,onSlideStart:t,onSlideMove:o,onSlideEnd:i,onHomeKeyDown:a,onEndKeyDown:c,onStepKeyDown:d,...f}=e,v=z($,r);return s.jsx(B.span,{...f,ref:n,onKeyDown:k(e.onKeyDown,u=>{u.key==="Home"?(a(u),u.preventDefault()):u.key==="End"?(c(u),u.preventDefault()):pe.concat(xe).includes(u.key)&&(d(u),u.preventDefault())}),onPointerDown:k(e.onPointerDown,u=>{const h=u.target;h.setPointerCapture(u.pointerId),u.preventDefault(),v.thumbs.has(h)?h.focus():t(u)}),onPointerMove:k(e.onPointerMove,u=>{u.target.hasPointerCapture(u.pointerId)&&o(u)}),onPointerUp:k(e.onPointerUp,u=>{const h=u.target;h.hasPointerCapture(u.pointerId)&&(h.releasePointerCapture(u.pointerId),i(u))})})}),je="SliderTrack",Ce=l.forwardRef((e,n)=>{const{__scopeSlider:r,...t}=e,o=z(je,r);return s.jsx(B.span,{"data-disabled":o.disabled?"":void 0,"data-orientation":o.orientation,...t,ref:n})});Ce.displayName=je;var K="SliderRange",Ee=l.forwardRef((e,n)=>{const{__scopeSlider:r,...t}=e,o=z(K,r),i=we(K,r),a=l.useRef(null),c=N(n,a),d=o.values.length,f=o.values.map(h=>Re(h,o.min,o.max)),v=d>1?Math.min(...f):0,u=100-Math.max(...f);return s.jsx(B.span,{"data-orientation":o.orientation,"data-disabled":o.disabled?"":void 0,...t,ref:c,style:{...e.style,[i.startEdge]:v+"%",[i.endEdge]:u+"%"}})});Ee.displayName=K;var W="SliderThumb",Pe=l.forwardRef((e,n)=>{const r=mt(e.__scopeSlider),[t,o]=l.useState(null),i=N(n,c=>o(c)),a=l.useMemo(()=>t?r().findIndex(c=>c.ref.current===t):-1,[r,t]);return s.jsx(vt,{...e,ref:i,index:a})}),vt=l.forwardRef((e,n)=>{const{__scopeSlider:r,index:t,name:o,...i}=e,a=z(W,r),c=we(W,r),[d,f]=l.useState(null),v=N(n,w=>f(w)),u=d?!!d.closest("form"):!0,h=at(d),x=a.values[t],p=x===void 0?0:Re(x,a.min,a.max),b=yt(t,a.values.length),m=h==null?void 0:h[c.size],S=m?Ct(m,p,c.direction):0;return l.useEffect(()=>{if(d)return a.thumbs.add(d),()=>{a.thumbs.delete(d)}},[d,a.thumbs]),s.jsxs("span",{style:{transform:"var(--radix-slider-thumb-transform)",position:"absolute",[c.startEdge]:`calc(${p}% + ${S}px)`},children:[s.jsx(F.ItemSlot,{scope:e.__scopeSlider,children:s.jsx(B.span,{role:"slider","aria-label":e["aria-label"]||b,"aria-valuemin":a.min,"aria-valuenow":x,"aria-valuemax":a.max,"aria-orientation":a.orientation,"data-orientation":a.orientation,"data-disabled":a.disabled?"":void 0,tabIndex:a.disabled?void 0:0,...i,ref:v,style:x===void 0?{display:"none"}:e.style,onFocus:k(e.onFocus,()=>{a.valueIndexToChangeRef.current=t})})}),u&&s.jsx(St,{name:o??(a.name?a.name+(a.values.length>1?"[]":""):void 0),value:x},t)]})});Pe.displayName=W;var St=e=>{const{value:n,...r}=e,t=l.useRef(null),o=st(n);return l.useEffect(()=>{const i=t.current,a=window.HTMLInputElement.prototype,d=Object.getOwnPropertyDescriptor(a,"value").set;if(o!==n&&d){const f=new Event("input",{bubbles:!0});d.call(i,n),i.dispatchEvent(f)}},[o,n]),s.jsx("input",{style:{display:"none"},...r,ref:t,defaultValue:n})};function wt(e=[],n,r){const t=[...e];return t[r]=n,t.sort((o,i)=>o-i)}function Re(e,n,r){const i=100/(r-n)*(e-n);return ue(i,[0,100])}function yt(e,n){return n>2?`Value ${e+1} of ${n}`:n===2?["Minimum","Maximum"][e]:void 0}function jt(e,n){if(e.length===1)return 0;const r=e.map(o=>Math.abs(o-n)),t=Math.min(...r);return r.indexOf(t)}function Ct(e,n,r){const t=e/2,i=G([0,50],[0,t]);return(t-i(n)*r)*r}function Et(e){return e.slice(0,-1).map((n,r)=>e[r+1]-n)}function Pt(e,n){if(n>0){const r=Et(e);return Math.min(...r)>=n}return!0}function G(e,n){return r=>{if(e[0]===e[1]||n[0]===n[1])return n[0];const t=(n[1]-n[0])/(e[1]-e[0]);return n[0]+t*(r-e[0])}}function Rt(e){return(String(e).split(".")[1]||"").length}function Nt(e,n){const r=Math.pow(10,n);return Math.round(e*r)/r}var Ne=ve,_t=Ce,Tt=Ee,te=Pe;const _e=({className:e,...n})=>s.jsxs(Ne,{className:"relative flex w-full touch-none select-none items-center "+e,...n,children:[s.jsx(_t,{className:"relative h-2 w-full grow overflow-hidden  bg-secondary bg-slate-400 rounded-full",children:s.jsx(Tt,{className:"absolute h-full bg-sky-400"})}),s.jsx(te,{className:"!block h-5 w-5 rounded-full border-2 bg-sky-400 bg-background ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50"}),s.jsx(te,{className:"block h-5 w-5 rounded-full border-2 bg-sky-400 bg-background ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50"})]});_e.displayName=Ne.displayName;const kt=async e=>{const n=await De.get($e+"category");if(n.status===200){const r=await n.data;console.log(r),e(r.childCategories)}},ne=()=>{const[e,n]=l.useState(),[r,t]=q();return l.useEffect(()=>{kt(n)},[]),s.jsxs("div",{className:"   w-100   flex-col gap-y-8 p-6 rounded-2xl flex",children:[s.jsxs("div",{className:"flex justify-between items-center cursor-pointer",children:[s.jsx("h3",{className:" text-xl font-semibold text-slate-700 cursor-pointer ",children:"فیلتر ها"}),s.jsx("div",{className:" text-sky-500 text-xs font-bold",onClick:()=>{t(o=>[])},children:"حذف فیلتر ها"})]}),s.jsxs("div",{className:"flex flex-col ",children:[s.jsxs("div",{className:"flex justify-between items-center cursor-pointer w-100 mb-2",children:[s.jsx("h5",{className:" text-sm font-medium text-slate-700  ",children:"دسته بندی"}),s.jsx("i",{class:"fa-duotone fa-angle-down ml-1"})]}),s.jsx(ie,{active:!0,categories:e})]}),s.jsxs("div",{className:"flex flex-col gap-y-3 ",children:[s.jsxs("div",{className:"flex justify-between items-center cursor-pointer ",children:[s.jsx("h5",{className:" text-sm font-medium text-slate-700 ",children:"محدوده قیمت"}),s.jsx("i",{class:"fa-duotone fa-angle-down ml-1"})]}),s.jsxs("div",{className:"flex flex-col",children:[s.jsxs("div",{className:"flex justify-between font-semibold",children:[s.jsx("span",{children:"قیمت از"}),s.jsxs("span",{children:[r.get("minPrice")," تومان"]})]}),s.jsxs("div",{className:"flex justify-between font-semibold",children:[s.jsx("span",{children:"تا"}),s.jsxs("span",{children:[r.get("maxPrice"),"تومان"]})]})]}),s.jsx(_e,{defaultValue:[r.get("minPrice")||1e7,r.get("maxPrice")||5e5],max:1e7,step:1e3,onValueCommit:o=>{let i=null,a=null;o.length===2&&(i=o[1],a=o[0]),t(c=>(c.set("maxPrice",i),c.set("minPrice",a),c))}})]}),s.jsxs("div",{className:"flex justify-between items-center cursor-pointer",children:[s.jsx("h5",{className:" text-sm font-semibold text-slate-700  ",children:"دارای تخفیف"}),s.jsx(de,{defaultChecked:r.get("enableOff")==="true"||!1,onCheckedChange:o=>{t(i=>(i.set("enableOff",o),i))}})]})]})};/*! *****************************************************************************
Copyright (c) Microsoft Corporation. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at http://www.apache.org/licenses/LICENSE-2.0

THIS CODE IS PROVIDED ON AN *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION ANY IMPLIED
WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A PARTICULAR PURPOSE,
MERCHANTABLITY OR NON-INFRINGEMENT.

See the Apache Version 2.0 License for specific language governing permissions
and limitations under the License.
***************************************************************************** */var U=function(e,n){return U=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(r,t){r.__proto__=t}||function(r,t){for(var o in t)t.hasOwnProperty(o)&&(r[o]=t[o])},U(e,n)};function Dt(e,n){U(e,n);function r(){this.constructor=e}e.prototype=n===null?Object.create(n):(r.prototype=n.prototype,new r)}var O=function(){return O=Object.assign||function(n){for(var r,t=1,o=arguments.length;t<o;t++){r=arguments[t];for(var i in r)Object.prototype.hasOwnProperty.call(r,i)&&(n[i]=r[i])}return n},O.apply(this,arguments)};function $t(e,n,r,t){var o,i=!1,a=0;function c(){o&&clearTimeout(o)}function d(){c(),i=!0}typeof n!="boolean"&&(t=r,r=n,n=void 0);function f(){var v=this,u=Date.now()-a,h=arguments;if(i)return;function x(){a=Date.now(),r.apply(v,h)}function p(){o=void 0}t&&!o&&x(),c(),t===void 0&&u>e?x():n!==!0&&(o=setTimeout(t?p:x,t===void 0?e-u:e))}return f.cancel=d,f}var D={Pixel:"Pixel",Percent:"Percent"},re={unit:D.Percent,value:.8};function oe(e){return typeof e=="number"?{unit:D.Percent,value:e*100}:typeof e=="string"?e.match(/^(\d*(\.\d+)?)px$/)?{unit:D.Pixel,value:parseFloat(e)}:e.match(/^(\d*(\.\d+)?)%$/)?{unit:D.Percent,value:parseFloat(e)}:(console.warn('scrollThreshold format is invalid. Valid formats: "120px", "50%"...'),re):(console.warn("scrollThreshold should be string or number"),re)}var Mt=function(e){Dt(n,e);function n(r){var t=e.call(this,r)||this;return t.lastScrollTop=0,t.actionTriggered=!1,t.startY=0,t.currentY=0,t.dragging=!1,t.maxPullDownDistance=0,t.getScrollableTarget=function(){return t.props.scrollableTarget instanceof HTMLElement?t.props.scrollableTarget:typeof t.props.scrollableTarget=="string"?document.getElementById(t.props.scrollableTarget):(t.props.scrollableTarget===null&&console.warn(`You are trying to pass scrollableTarget but it is null. This might
        happen because the element may not have been added to DOM yet.
        See https://github.com/ankeetmaini/react-infinite-scroll-component/issues/59 for more info.
      `),null)},t.onStart=function(o){t.lastScrollTop||(t.dragging=!0,o instanceof MouseEvent?t.startY=o.pageY:o instanceof TouchEvent&&(t.startY=o.touches[0].pageY),t.currentY=t.startY,t._infScroll&&(t._infScroll.style.willChange="transform",t._infScroll.style.transition="transform 0.2s cubic-bezier(0,0,0.31,1)"))},t.onMove=function(o){t.dragging&&(o instanceof MouseEvent?t.currentY=o.pageY:o instanceof TouchEvent&&(t.currentY=o.touches[0].pageY),!(t.currentY<t.startY)&&(t.currentY-t.startY>=Number(t.props.pullDownToRefreshThreshold)&&t.setState({pullToRefreshThresholdBreached:!0}),!(t.currentY-t.startY>t.maxPullDownDistance*1.5)&&t._infScroll&&(t._infScroll.style.overflow="visible",t._infScroll.style.transform="translate3d(0px, "+(t.currentY-t.startY)+"px, 0px)")))},t.onEnd=function(){t.startY=0,t.currentY=0,t.dragging=!1,t.state.pullToRefreshThresholdBreached&&(t.props.refreshFunction&&t.props.refreshFunction(),t.setState({pullToRefreshThresholdBreached:!1})),requestAnimationFrame(function(){t._infScroll&&(t._infScroll.style.overflow="auto",t._infScroll.style.transform="none",t._infScroll.style.willChange="unset")})},t.onScrollListener=function(o){typeof t.props.onScroll=="function"&&setTimeout(function(){return t.props.onScroll&&t.props.onScroll(o)},0);var i=t.props.height||t._scrollableNode?o.target:document.documentElement.scrollTop?document.documentElement:document.body;if(!t.actionTriggered){var a=t.props.inverse?t.isElementAtTop(i,t.props.scrollThreshold):t.isElementAtBottom(i,t.props.scrollThreshold);a&&t.props.hasMore&&(t.actionTriggered=!0,t.setState({showLoader:!0}),t.props.next&&t.props.next()),t.lastScrollTop=i.scrollTop}},t.state={showLoader:!1,pullToRefreshThresholdBreached:!1,prevDataLength:r.dataLength},t.throttledOnScrollListener=$t(150,t.onScrollListener).bind(t),t.onStart=t.onStart.bind(t),t.onMove=t.onMove.bind(t),t.onEnd=t.onEnd.bind(t),t}return n.prototype.componentDidMount=function(){if(typeof this.props.dataLength>"u")throw new Error('mandatory prop "dataLength" is missing. The prop is needed when loading more content. Check README.md for usage');if(this._scrollableNode=this.getScrollableTarget(),this.el=this.props.height?this._infScroll:this._scrollableNode||window,this.el&&this.el.addEventListener("scroll",this.throttledOnScrollListener),typeof this.props.initialScrollY=="number"&&this.el&&this.el instanceof HTMLElement&&this.el.scrollHeight>this.props.initialScrollY&&this.el.scrollTo(0,this.props.initialScrollY),this.props.pullDownToRefresh&&this.el&&(this.el.addEventListener("touchstart",this.onStart),this.el.addEventListener("touchmove",this.onMove),this.el.addEventListener("touchend",this.onEnd),this.el.addEventListener("mousedown",this.onStart),this.el.addEventListener("mousemove",this.onMove),this.el.addEventListener("mouseup",this.onEnd),this.maxPullDownDistance=this._pullDown&&this._pullDown.firstChild&&this._pullDown.firstChild.getBoundingClientRect().height||0,this.forceUpdate(),typeof this.props.refreshFunction!="function"))throw new Error(`Mandatory prop "refreshFunction" missing.
          Pull Down To Refresh functionality will not work
          as expected. Check README.md for usage'`)},n.prototype.componentWillUnmount=function(){this.el&&(this.el.removeEventListener("scroll",this.throttledOnScrollListener),this.props.pullDownToRefresh&&(this.el.removeEventListener("touchstart",this.onStart),this.el.removeEventListener("touchmove",this.onMove),this.el.removeEventListener("touchend",this.onEnd),this.el.removeEventListener("mousedown",this.onStart),this.el.removeEventListener("mousemove",this.onMove),this.el.removeEventListener("mouseup",this.onEnd)))},n.prototype.componentDidUpdate=function(r){this.props.dataLength!==r.dataLength&&(this.actionTriggered=!1,this.setState({showLoader:!1}))},n.getDerivedStateFromProps=function(r,t){var o=r.dataLength!==t.prevDataLength;return o?O(O({},t),{prevDataLength:r.dataLength}):null},n.prototype.isElementAtTop=function(r,t){t===void 0&&(t=.8);var o=r===document.body||r===document.documentElement?window.screen.availHeight:r.clientHeight,i=oe(t);return i.unit===D.Pixel?r.scrollTop<=i.value+o-r.scrollHeight+1:r.scrollTop<=i.value/100+o-r.scrollHeight+1},n.prototype.isElementAtBottom=function(r,t){t===void 0&&(t=.8);var o=r===document.body||r===document.documentElement?window.screen.availHeight:r.clientHeight,i=oe(t);return i.unit===D.Pixel?r.scrollTop+o>=r.scrollHeight-i.value:r.scrollTop+o>=i.value/100*r.scrollHeight},n.prototype.render=function(){var r=this,t=O({height:this.props.height||"auto",overflow:"auto",WebkitOverflowScrolling:"touch"},this.props.style),o=this.props.hasChildren||!!(this.props.children&&this.props.children instanceof Array&&this.props.children.length),i=this.props.pullDownToRefresh&&this.props.height?{overflow:"auto"}:{};return C.createElement("div",{style:i,className:"infinite-scroll-component__outerdiv"},C.createElement("div",{className:"infinite-scroll-component "+(this.props.className||""),ref:function(a){return r._infScroll=a},style:t},this.props.pullDownToRefresh&&C.createElement("div",{style:{position:"relative"},ref:function(a){return r._pullDown=a}},C.createElement("div",{style:{position:"absolute",left:0,right:0,top:-1*this.maxPullDownDistance}},this.state.pullToRefreshThresholdBreached?this.props.releaseToRefreshContent:this.props.pullDownToRefreshContent)),this.props.children,!this.state.showLoader&&!o&&this.props.hasMore&&this.props.loader,this.state.showLoader&&this.props.hasMore&&this.props.loader,!this.props.hasMore&&this.props.endMessage))},n}(l.Component);const A=e=>s.jsx("div",{style:{width:e.width,height:e.height},className:"skeleton "+(e.variant==="circle"?"rounded-full":"rounded")+" "+e.className}),Lt=()=>s.jsx(s.Fragment,{children:Array.from({length:10}).map((e,n)=>s.jsxs("div",{className:"flex flex-col p-3 gap-y-3 border border-slate-200  hover:shadow-lg cursor-pointer",children:[s.jsx("div",{className:"mx-auto mt-4",children:s.jsx(A,{className:"h-[240px] w-[240px]"})}),s.jsx(A,{className:"h-[10px] w-9/12   "}),s.jsx(A,{className:"h-[10px] w-4/12   "}),s.jsx("div",{className:"flex justify-between items-center ",children:s.jsx("div",{className:"text-xs",children:s.jsx(A,{className:"h-[10px] w-10   "})})}),s.jsx("div",{className:"flex justify-end text-base font-bold text-slate-600 mb-2",children:s.jsx(A,{className:"h-[10px] w-24   "})})]}))}),At=()=>{const[e,n]=l.useState(1),[r,t]=l.useState(!0),[o,i]=l.useState([]),[a,c]=q();return l.useEffect(()=>{console.log("meow"),ee(Object.fromEntries([...a]),[],i,1,n,t)},[a]),s.jsx("div",{className:"grow",children:s.jsx(Mt,{dataLength:o.length,next:()=>ee(Object.fromEntries([...a]),o,i,e,n,t),hasMore:r,loader:s.jsx(Lt,{}),className:"h-96  grow lg:mr-5 grid grid-cols-1 xl:grid-cols-4 md:grid-cols-3 ",children:o==null?void 0:o.map((d,f)=>s.jsxs("div",{className:"flex flex-col p-3 gap-y-3 border border-slate-200  hover:shadow-lg cursor-pointer",children:[s.jsx("div",{className:"mx-auto mt-4",children:s.jsx("img",{className:"h-[240px] w-[240px]",src:ze})}),s.jsx("div",{className:"h-[72px] overflow-hidden font-medium  text-xs text-slate-700 !leading-7 ",children:d==null?void 0:d.name}),s.jsx("div",{className:"flex justify-between items-center ",children:s.jsxs("div",{className:"text-xs",children:[s.jsx("span",{className:"mx-2 font-bold text-slate-800",children:"۴.۴"}),s.jsx("i",{class:"fa-duotone fa-star text-amber-600"})]})}),s.jsxs("div",{className:"flex justify-end text-base font-bold text-slate-600 mb-2",children:[s.jsx("span",{className:"mx-1",children:"۶۴,۰۰۰,۰۰۰"}),s.jsx("span",{children:"تومان"})]})]}))})})},Ot=e=>{const n=l.useRef(),r=l.useRef(),t=l.useRef(e.side?e.side:"bottom"),o=i=>{i=="top"?n.current.classList.add("data-[state=closed]:-translate-y-full","top-0","w-100"):i=="bottom"?n.current.classList.add("data-[state=closed]:translate-y-full","bottom-0","w-100"):i=="left"?n.current.classList.add("data-[state=closed]:-translate-x-full","left-0","h-100"):i=="right"&&n.current.classList.add("data-[state=closed]:translate-x-full","right-0","h-100")};return l.useEffect(()=>{o(t.current)},[]),s.jsx("div",{"data-state":e.state?e.state:"closed",ref:n,className:"fixed flex  overflow-hidden flex-nowrap duration-500  border-2  "+e.className,children:s.jsxs("div",{ref:r,className:" w-100 h-100   p-3 relative w-0   "+e.borderType+" "+e.moreClass,children:[s.jsx("div",{onClick:()=>{e.setState("closed")},role:"button",className:" dialog-close-btn ",children:s.jsx("i",{class:"fa fa-times","aria-hidden":"true"})}),e.children]})})},Wt=()=>{const[e,n]=l.useState(!1);return s.jsxs("div",{className:"search-page",children:[s.jsx(Le,{}),s.jsx(Me,{}),s.jsx(Ye,{onClick:()=>{n(!0)},prefix:s.jsx("i",{class:"fa-solid fa-filter-list"}),border:!0,size:"sm",morCss:"mt-2 lg:hidden !font-medium",children:"فیلتر"}),s.jsxs("div",{className:"container mx-auto max-w-screen-2xl flex  mt-5   ",children:[s.jsx("div",{className:"min-w-72 hidden lg:flex border",children:s.jsx(ne,{})}),s.jsx(Ot,{state:e,setState:n,className:" lg:hidden z-50 bg-white h-100",children:s.jsx(ne,{})}),s.jsx(At,{})]})]})};export{Wt as default};
