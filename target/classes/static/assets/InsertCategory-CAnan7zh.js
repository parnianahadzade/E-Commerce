import{b as d,j as s,r as m}from"./index-B5K_8oeC.js";import{A as x}from"./AdminSideBar-DNalYHWP.js";import{S as p}from"./SelectCategoryList-CxcP0xSs.js";import{c as f}from"./categories-CQWqfNSF.js";import{I as g,B as j}from"./Input-Buyegn0d.js";import{f as h}from"./form-CHX7R2_W.js";import{M as u}from"./MobileFooter-DnFNEP7P.js";import{N}from"./NavBar-CxD-nayd.js";import{A as v}from"./Alert-DHZI-b21.js";const y=({errors:e,setToastList:r,setErrors:a})=>{const[t,l]=d(),c=o=>{o.preventDefault();const n=o.target.name.value,i=t.get("categoryId");h("category/admin/save",{name:n,parentId:i},r,a)};return s.jsxs("form",{onSubmit:c,className:"insert-product-form flex flex-col gap-y-10",children:[s.jsxs("div",{className:"flex ",children:[s.jsx("div",{className:"mx-2 self-start",children:s.jsx("i",{class:"fa-solid fa-2x fa-square-plus text-sky-400"})}),s.jsxs("div",{className:"grow flex flex-col gap-y-3",children:[s.jsx("div",{className:" text-lg font-semibold",children:"انتخاب گروه پدر"}),s.jsx("div",{className:"mx-3",children:s.jsx(p,{categories:f})}),e.parentId?e.parentId:s.jsx("span",{className:" text-red-600 text-sm"})]})]}),s.jsxs("div",{className:"flex flex-col col-span-6 ",children:[s.jsxs("div",{className:"mb-2 font-medium text-sm !leading-3 ",children:[s.jsx("span",{className:" text-red-500 text-lg !leading-3 ",children:"*"}),s.jsx("span",{className:"!leading-3",children:"نام گروه کالایی"})]}),s.jsx(g,{name:"name",iMessage:e==null?void 0:e.name})]}),s.jsx(j,{bgColor:"bg-sky-100",txtColor:"text-sky-800",moreCss:"border-sky-400",children:"ثبت گروه کالایی"})]})},F=()=>{const[e,r]=m.useState([]),[a,t]=m.useState([]);return s.jsxs("div",{className:"profile-page",children:[s.jsx(N,{}),s.jsx("div",{className:" sticky top-24 w-100 h-0 ",children:a==null?void 0:a.map(l=>s.jsx(v,{duration:5e3,type:l.type,children:l.message}))}),s.jsx(u,{}),s.jsxs("div",{className:"mx-auto max-w-screen-xl grid grid-cols-7  mt-10",children:[s.jsx("div",{className:"lg:col-span-2 lg:order-1 col-span-12 order-2",children:s.jsx(x,{})}),s.jsx("div",{className:"lg:col-span-5 lg:order-2 order-1 col-span-12 lg:mr-4",children:s.jsx(y,{errors:e,setErrors:r,setToastList:t})})]})]})};export{F as default};
