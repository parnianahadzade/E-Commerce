import{j as e,a as f,r as l,b as j,i as u}from"./index-C88s8bSQ.js";import{M as g}from"./MobileFooter-D7dY_Xl2.js";import{N,T as v}from"./NavBar-CNJfXYLX.js";import{I as b,S as m}from"./skeleton-CLxeKTPl.js";import{s as h}from"./productApi-Dl9Jr5DU.js";import"./Input-B77FTipe.js";import"./index-DSDwgsHt.js";const i=s=>e.jsx("div",{style:{width:s.width,height:s.height},className:"skeleton "+(s.variant==="circle"?"rounded-full":"rounded")+" "+s.className}),w=()=>e.jsx(e.Fragment,{children:Array.from({length:10}).map((s,t)=>e.jsxs("div",{className:"flex flex-col p-3 gap-y-3 border border-slate-200  hover:shadow-lg cursor-pointer",children:[e.jsx("div",{className:"mx-auto mt-4",children:e.jsx(i,{className:"h-[240px] w-[240px]"})}),e.jsx(i,{className:"h-[10px] w-9/12   "}),e.jsx(i,{className:"h-[10px] w-4/12   "}),e.jsx("div",{className:"flex justify-between items-center ",children:e.jsx("div",{className:"text-xs",children:e.jsx(i,{className:"h-[10px] w-10   "})})}),e.jsx("div",{className:"flex justify-end text-base font-bold text-slate-600 mb-2",children:e.jsx(i,{className:"h-[10px] w-24   "})})]}))}),S=()=>{const s=f(),[t,c]=l.useState(1),[d,n]=l.useState(!0),[a,x]=l.useState([]),[o,p]=j();return l.useEffect(()=>{console.log("meow"),h(Object.fromEntries([...o]),[],x,1,c,n)},[o]),e.jsx("div",{className:"grow",children:e.jsx(b,{dataLength:a.length,next:()=>h(Object.fromEntries([...o]),a,x,t,c,n),hasMore:d,loader:e.jsx(w,{}),className:"h-96  grow lg:mr-5 grid grid-cols-1 xl:grid-cols-4 md:grid-cols-3 ",children:a==null?void 0:a.map((r,P)=>e.jsxs("div",{onClick:()=>s("/product/"+r.id),className:"flex flex-col p-3 gap-y-3 border border-slate-200  hover:shadow-lg cursor-pointer",children:[e.jsx("div",{className:"mx-auto mt-4",children:e.jsx("img",{className:"h-[240px] w-[240px]",src:u+(r==null?void 0:r.mainImage.filePath)})}),e.jsx("div",{className:"h-[72px] overflow-hidden font-medium  text-xs text-slate-700 !leading-7 ",children:r==null?void 0:r.name}),e.jsx("div",{className:"flex justify-between items-center ",children:e.jsxs("div",{className:"text-xs",children:[e.jsx("span",{className:"mx-2 font-bold text-slate-800",children:"۴.۴"}),e.jsx("i",{class:"fa-duotone fa-star text-amber-600"})]})}),e.jsxs("div",{className:"flex justify-end text-base font-bold text-slate-600 mb-2",children:[e.jsx("span",{className:"mx-1",children:"۶۴,۰۰۰,۰۰۰"}),e.jsx("span",{children:"تومان"})]})]}))})})},y=s=>{const t=l.useRef(),c=l.useRef(),d=l.useRef(s.side?s.side:"bottom"),n=a=>{a=="top"?t.current.classList.add("data-[state=closed]:-translate-y-full","top-0","w-100"):a=="bottom"?t.current.classList.add("data-[state=closed]:translate-y-full","bottom-0","w-100"):a=="left"?t.current.classList.add("data-[state=closed]:-translate-x-full","left-0","h-100"):a=="right"&&t.current.classList.add("data-[state=closed]:translate-x-full","right-0","h-100")};return l.useEffect(()=>{n(d.current)},[]),e.jsx("div",{"data-state":s.state?s.state:"closed",ref:t,className:"fixed flex  overflow-hidden flex-nowrap duration-500  border-2  "+s.className,children:e.jsxs("div",{ref:c,className:" w-100 h-100   p-3 relative w-0   "+s.borderType+" "+s.moreClass,children:[e.jsx("div",{onClick:()=>{s.setState("closed")},role:"button",className:" dialog-close-btn ",children:e.jsx("i",{class:"fa fa-times","aria-hidden":"true"})}),s.children]})})},M=()=>{const[s,t]=l.useState(!1);return e.jsxs("div",{className:"search-page",children:[e.jsx(N,{}),e.jsx(g,{}),e.jsx(v,{onClick:()=>{t(!0)},prefix:e.jsx("i",{class:"fa-solid fa-filter-list"}),border:!0,size:"sm",morCss:"mt-2 lg:hidden !font-medium",children:"فیلتر"}),e.jsxs("div",{className:"container mx-auto max-w-screen-2xl flex  mt-5   ",children:[e.jsx("div",{className:"min-w-72 hidden lg:flex border",children:e.jsx(m,{})}),e.jsx(y,{state:s,setState:t,className:" lg:hidden z-50 bg-white h-100",children:e.jsx(m,{})}),e.jsx(S,{})]})]})};export{M as default};
