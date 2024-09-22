import{r as n,A as d,j as e}from"./index-zi03S1sp.js";import{M as N}from"./MobileFooter-C2Yc4Vkx.js";import{N as v}from"./NavBar-Cfb4iYIu.js";import{f as b}from"./productApi-AdcDZgPp.js";import{B as u}from"./Input-gxYtp5Ku.js";const w="/assets/SpecialSell-CG2JfrZX.svg",y=({productInCart:l})=>{var x,r;const[s,a]=n.useState(),[t,o]=n.useState(),{shoppingCart:m,findProductInCart:S,addToCart:B,sumProductInCart:h,subtractProductInCart:j,isProductInCartValid:p,deleteProductFromCart:f}=n.useContext(d);return n.useEffect(()=>{b(l.productId,a)},[]),n.useEffect(()=>{var c;const i=s==null?void 0:s.inventories.find(g=>g.id===l.productInCart.inventoryId);console.log(i),p(i==null?void 0:i.quantity,(c=l==null?void 0:l.productInCart)==null?void 0:c.quantity)?o(i):f(m,l.productInCartIndex)},[s]),e.jsx("div",{className:" border-b px-1 py-5",children:e.jsxs("div",{className:"flex",children:[e.jsxs("div",{className:"flex flex-col items-center",children:[e.jsx("img",{className:"w-[114px] h-[114px] mb-3",src:(x=s==null?void 0:s.mainImage)==null?void 0:x.filePath}),e.jsx("img",{className:"w-[60px] ",src:w}),e.jsxs("div",{className:"border rounded-md flex justify-between grow px-2 py-1 mt-4 gap-x-3 text-red-500 items-center cursor-pointer select-none ",children:[e.jsx("span",{onClick:()=>{h(l,t.quantity)},children:"+"}),e.jsx("span",{children:(r=l==null?void 0:l.productInCart)==null?void 0:r.quantity}),e.jsx("span",{onClick:()=>{j(l)},children:"-"})]})]}),e.jsxs("div",{className:"flex flex-col mr-3",children:[e.jsx("span",{className:" font-semibold mb-2",children:s==null?void 0:s.name}),e.jsxs("div",{className:"flex flex-col text-sm font-medium text-neutral-500",children:[e.jsx("span",{children:"گارانتی اصالت و سلامت فیزیکی کالا"}),e.jsxs("span",{children:["سایز ",t==null?void 0:t.size]}),e.jsx("span",{children:"ارسال دیجی‌کالا از ۲ روز کاری دیگر"})]}),e.jsxs("div",{children:[e.jsxs("div",{className:"gap-x-1 flex  text-xs text-rose-600 mt-3 font-medium",children:[e.jsx("span",{children:s==null?void 0:s.price}),e.jsx("span",{children:"تومان"}),e.jsx("span",{children:"تخفیف"})]}),e.jsxs("div",{className:" flex text-lg gap-x-1  mt-3 font-semibold",children:[e.jsx("span",{children:"۳۰,۰۰۰"}),e.jsx("span",{children:"تومان"})]})]})]})]})})},C=()=>e.jsxs("div",{className:"lg:w-[300px] w-screen left-0 bg-white  justify-between items-center lg:items-stretch fixed bottom-[86px] flex lg:flex-col gap-y-4 border p-5 lg:sticky lg:top-[155px]",children:[e.jsxs("div",{className:"lg:flex   hidden justify-between text-neutral-500 font-semibold  ",children:[e.jsx("span",{children:"قیمت کالا ها (۲)"}),e.jsxs("div",{children:[e.jsx("span",{className:"ml-1",children:"۲,۷۶۰,۰۰۰"}),e.jsx("span",{children:"تومان"})]})]}),e.jsxs("div",{className:"flex lg:flex-row flex-col justify-between items-center  font-semibold lg:order-none order-2  ",children:[e.jsx("span",{children:"جمع سبد خرید"}),e.jsxs("div",{children:[e.jsx("span",{className:"ml-1",children:"۲,۷۶۰,۰۰۰"}),e.jsx("span",{children:"تومان"})]})]}),e.jsxs("div",{className:"lg:flex hidden justify-between text-rose-600 font-semibold   ",children:[e.jsx("span",{children:"سود شما از خرید"}),e.jsxs("div",{children:[e.jsx("span",{className:"ml-1",children:"۲,۷۶۰,۰۰۰"}),e.jsx("span",{children:"تومان"})]})]}),e.jsx(u,{size:"lg",shape:"rounded-xl",bgColor:"bg-rose-500",txtColor:"text-white",children:"تایید و تکمیل سفارش"})]}),A=()=>{const{shoppingCart:l}=n.useContext(d);return e.jsxs("div",{className:"cart-page",children:[e.jsx(v,{}),e.jsx(N,{}),e.jsx("div",{className:"mx-auto max-w-screen-xl",children:e.jsxs("div",{className:"flex mt-10",children:[e.jsxs("div",{className:"grow flex flex-col border rounded-md ml-3 px-7 py-3",children:[e.jsxs("div",{className:"flex flex-col",children:[e.jsxs("div",{className:"flex justify-between",children:[e.jsx("span",{className:" font-semibold text-lg",children:"سبد خرید شما"}),e.jsx("div",{children:"three dot"})]}),e.jsxs("span",{className:" text-neutral-500 text-sm",children:[l.length," کالا"]})]}),l.map((s,a)=>e.jsx(y,{productInCart:{productInCart:s,productInCartIndex:a}}))]}),e.jsx("div",{children:e.jsx(C,{})})]})})]})};export{A as default};
