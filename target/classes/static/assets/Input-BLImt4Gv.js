import{r as n,j as t}from"./index-BqyAqRa7.js";const x=e=>{const[a,l]=n.useState(" px-4 py-2 h6 "),[d,s]=n.useState(" rounded ");return n.useEffect(()=>{if(console.log(e.moreCss),e.size){const i=e.size;i==="lg"?l("px-8 py-3 text-base font-bold "):i==="md"?l("px-6 py-2 text-sm font-semibold"):i==="sm"?l("px-4  py-2 text-sm font-medium"):i==="xs"&&l("px-4  py-1 text-xs font-normal ")}e.shape&&(s(e.shape),e.shape==="rounded-full"&&l("h6 p-2"))},[]),t.jsxs("button",{...e,disabled:e.disabled,type:e.type,onClick:e.onClick,className:"   duration-100   button flex items-center justify-center "+(e.border?e.border:"border")+" "+e.hoverClass+" "+a+" "+d+" "+e.bgColor+" "+e.txtColor+" "+(e.disabled?" text-slate-500":"")+" "+(e.col?"flex-col":"")+" "+e.moreCss,children:[e.icon?t.jsx("div",{className:e.col?"mb-1":"ml-1",children:e.icon}):t.jsx(t.Fragment,{}),e.children,e.leftIcon?t.jsx("div",{className:e.col?"mb-1":"mr-1",children:e.leftIcon}):t.jsx(t.Fragment,{})]})},m=e=>{const a=()=>{if(e.invalid)return t.jsx("div",{className:"mx-1  h-100 flex items-center absolute left-0 text-bf-red",children:t.jsx("svg",{stroke:"currentColor",fill:"currentColor","stroke-width":"0",viewBox:"0 0 20 20","aria-hidden":"true",class:"text-red-500 text-xl",height:"1em",width:"1em",xmlns:"http://www.w3.org/2000/svg",children:t.jsx("path",{"fill-rule":"evenodd",d:"M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7 4a1 1 0 11-2 0 1 1 0 012 0zm-1-9a1 1 0 00-1 1v4a1 1 0 102 0V6a1 1 0 00-1-1z","clip-rule":"evenodd"})})});if(e.affix)return t.jsx("div",{className:"mx-1  h-100 flex items-center absolute left-0",children:e.affix})};return t.jsxs("div",{style:{width:e.width},className:"flex flex-col",children:[t.jsxs("div",{className:" relative  input-container  flex items-center   "+(e.disabled?"sub-txt-color":"")+" "+e.inputclassName,children:[e.preffix?t.jsx("div",{className:"mx-2  h-100 flex items-center absolute right-0 ",children:e.preffix}):t.jsx(t.Fragment,{}),e.type==="textarea"?t.jsx("textarea",{onChange:e.onChange,value:e.value,disabled:e.disabled,type:e.type,placeholder:e.placeHolder,className:" flex-grow  custom-input    rounded w-100 "+(e.invalid?"border-bf-red  ":""),style:{height:e.height}}):t.jsx("input",{name:e.name,onBlur:e.onBlur,onChange:e.onChange,value:e.value,disabled:e.disabled,type:e.type,placeholder:e.placeHolder,className:" flex-grow  custom-input    rounded w-100 "+(e.invalid?"border-bf-red  ":""),style:{height:e.height}}),a()]}),t.jsx("div",{className:"text-bf-red  mt-1  transition-400 font-semibold overflow-hidden  "+(e.invalid?"h-5":"h-0"),children:e.iMessage})]})};export{x as B,m as I};
