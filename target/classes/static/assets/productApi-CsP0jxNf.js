import{f as c,s as n}from"./index-eps9604u.js";const i=async(o,e,s,a,f,l)=>{const r=await c.get(n+"product",{params:{...o,pageNumber:a}});if(r.status===200){const t=await r.data;if(console.log(t),t.length===0){l(!1),e.length===0&&s([]);return}f(a+1),s([...e,...t]),l(!0)}else console.log("hello")},u=async(o,e)=>{const s=await c.get(n+"product/"+o);if(s.status===200){const a=await s.data;console.log(a),e(a)}else console.log("hello")};export{u as f,i as s};
