import{j as s,r}from"./index-B5K_8oeC.js";import{A as d}from"./AdminSideBar-DNalYHWP.js";import{I as i,B as c}from"./Input-Buyegn0d.js";import{f as x}from"./form-CHX7R2_W.js";import{M as p}from"./MobileFooter-DnFNEP7P.js";import{N as g}from"./NavBar-CxD-nayd.js";import{A as j}from"./Alert-DHZI-b21.js";const f=({errors:e,setToastList:n,setErrors:l})=>{const t=a=>{a.preventDefault();const m=a.target.name.value,o=a.target.explanation.value;x("brand/admin/save",{name:m,explanation:o},n,l)};return s.jsxs("form",{onSubmit:t,className:"insert-product-form flex flex-col gap-y-10",children:[s.jsxs("div",{className:" grid grid-cols-12",children:[s.jsxs("div",{className:"flex flex-col col-span-6 ml-5 ",children:[s.jsxs("div",{className:"mb-2 font-medium text-sm !leading-3 ",children:[s.jsx("span",{className:" text-red-500 text-lg !leading-3 ",children:"*"}),s.jsx("span",{className:"!leading-3",children:"نام برند"})]}),s.jsx(i,{name:"name",iMessage:e==null?void 0:e.name})]}),s.jsxs("div",{className:"flex flex-col col-span-12 ",children:[s.jsxs("div",{className:"mb-2 font-medium text-sm !leading-3 ",children:[s.jsx("span",{className:" text-red-500 text-lg !leading-3 ",children:"*"}),s.jsx("span",{className:"!leading-3",children:"توضحات"})]}),s.jsx(i,{iMessage:e==null?void 0:e.explanation,name:"explanation",type:"textarea"})]})]}),s.jsx(c,{bgColor:"bg-sky-100",txtColor:"text-sky-800",moreCss:"border-sky-400",children:"ثبت برند"})]})},A=()=>{const[e,n]=r.useState([]),[l,t]=r.useState([]);return s.jsxs("div",{className:"profile-page",children:[s.jsx(g,{}),s.jsx("div",{className:" sticky top-24 w-100 h-0 ",children:l==null?void 0:l.map(a=>s.jsx(j,{duration:5e3,type:a.type,children:a.message}))}),s.jsx(p,{}),s.jsxs("div",{className:"mx-auto max-w-screen-xl grid grid-cols-7  mt-10",children:[s.jsx("div",{className:"lg:col-span-2 lg:order-1 col-span-12 order-2",children:s.jsx(d,{})}),s.jsx("div",{className:"lg:col-span-5 lg:order-2 order-1 col-span-12 lg:mr-4",children:s.jsx(f,{errors:e,setErrors:n,setToastList:t})})]})]})};export{A as default};
