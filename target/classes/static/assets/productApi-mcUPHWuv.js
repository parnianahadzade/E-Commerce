import{r as a,f as h,s as b}from"./index-BnAxZSSR.js";function w(e,t,{checkForDefaultPrevented:o=!0}={}){return function(c){if(e==null||e(c),o===!1||!c.defaultPrevented)return t==null?void 0:t(c)}}function _(e,t=[]){let o=[];function n(r,u){const s=a.createContext(u),l=o.length;o=[...o,u];function f(i){const{scope:d,children:v,...$}=i,g=(d==null?void 0:d[e][l])||s,m=a.useMemo(()=>$,Object.values($));return a.createElement(g.Provider,{value:m},v)}function p(i,d){const v=(d==null?void 0:d[e][l])||s,$=a.useContext(v);if($)return $;if(u!==void 0)return u;throw new Error(`\`${i}\` must be used within \`${r}\``)}return f.displayName=r+"Provider",[f,p]}const c=()=>{const r=o.map(u=>a.createContext(u));return function(s){const l=(s==null?void 0:s[e])||r;return a.useMemo(()=>({[`__scope${e}`]:{...s,[e]:l}}),[s,l])}};return c.scopeName=e,[n,C(c,...t)]}function C(...e){const t=e[0];if(e.length===1)return t;const o=()=>{const n=e.map(c=>({useScope:c(),scopeName:c.scopeName}));return function(r){const u=n.reduce((s,{useScope:l,scopeName:f})=>{const i=l(r)[`__scope${f}`];return{...s,...i}},{});return a.useMemo(()=>({[`__scope${t.scopeName}`]:u}),[u])}};return o.scopeName=t.scopeName,o}const y=globalThis!=null&&globalThis.document?a.useLayoutEffect:()=>{};function x(e){const t=a.useRef(e);return a.useEffect(()=>{t.current=e}),a.useMemo(()=>(...o)=>{var n;return(n=t.current)===null||n===void 0?void 0:n.call(t,...o)},[])}function R({prop:e,defaultProp:t,onChange:o=()=>{}}){const[n,c]=P({defaultProp:t,onChange:o}),r=e!==void 0,u=r?e:n,s=x(o),l=a.useCallback(f=>{if(r){const i=typeof f=="function"?f(e):f;i!==e&&s(i)}else c(f)},[r,e,c,s]);return[u,l]}function P({defaultProp:e,onChange:t}){const o=a.useState(e),[n]=o,c=a.useRef(n),r=x(t);return a.useEffect(()=>{c.current!==n&&(r(n),c.current=n)},[n,c,r]),o}const k=async(e,t,o,n,c,r)=>{console.log(n),console.log(t);const u=await h.get(b+"product",{params:{...e,pageNumber:n}});if(u.status===200){const s=await u.data;if(console.log(s),s.length===0){r(!1),t.length===0&&o([]);return}c(n+1),o([...t,...s]),r(!0)}else console.log("hello")},D=async(e,t)=>{const o=await h.get(b+"product/"+e);if(o.status===200){const n=await o.data;console.log(n),t(n)}else console.log("hello")};export{_ as $,y as a,R as b,x as c,w as d,D as f,k as s};
