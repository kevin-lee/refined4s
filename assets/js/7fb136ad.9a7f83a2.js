"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[923],{2527:(e,n,s)=>{s.r(n),s.d(n,{assets:()=>V,contentTitle:()=>D,default:()=>M,frontMatter:()=>I,metadata:()=>A,toc:()=>E});var i=s(4848),r=s(8453),l=s(6540),a=s(4164),d=s(3104),c=s(6347),t=s(205),o=s(7485),h=s(1682),u=s(9466);function f(e){return l.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,l.isValidElement)(e)&&function(e){const{props:n}=e;return!!n&&"object"==typeof n&&"value"in n}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function b(e){const{values:n,children:s}=e;return(0,l.useMemo)((()=>{const e=n??function(e){return f(e).map((e=>{let{props:{value:n,label:s,attributes:i,default:r}}=e;return{value:n,label:s,attributes:i,default:r}}))}(s);return function(e){const n=(0,h.X)(e,((e,n)=>e.value===n.value));if(n.length>0)throw new Error(`Docusaurus error: Duplicate values "${n.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[n,s])}function p(e){let{value:n,tabValues:s}=e;return s.some((e=>e.value===n))}function x(e){let{queryString:n=!1,groupId:s}=e;const i=(0,c.W6)(),r=function(e){let{queryString:n=!1,groupId:s}=e;if("string"==typeof n)return n;if(!1===n)return null;if(!0===n&&!s)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return s??null}({queryString:n,groupId:s});return[(0,o.aZ)(r),(0,l.useCallback)((e=>{if(!r)return;const n=new URLSearchParams(i.location.search);n.set(r,e),i.replace({...i.location,search:n.toString()})}),[r,i])]}function j(e){const{defaultValue:n,queryString:s=!1,groupId:i}=e,r=b(e),[a,d]=(0,l.useState)((()=>function(e){let{defaultValue:n,tabValues:s}=e;if(0===s.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(n){if(!p({value:n,tabValues:s}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${n}" but none of its children has the corresponding value. Available values are: ${s.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return n}const i=s.find((e=>e.default))??s[0];if(!i)throw new Error("Unexpected error: 0 tabValues");return i.value}({defaultValue:n,tabValues:r}))),[c,o]=x({queryString:s,groupId:i}),[h,f]=function(e){let{groupId:n}=e;const s=function(e){return e?`docusaurus.tab.${e}`:null}(n),[i,r]=(0,u.Dv)(s);return[i,(0,l.useCallback)((e=>{s&&r.set(e)}),[s,r])]}({groupId:i}),j=(()=>{const e=c??h;return p({value:e,tabValues:r})?e:null})();(0,t.A)((()=>{j&&d(j)}),[j]);return{selectedValue:a,selectValue:(0,l.useCallback)((e=>{if(!p({value:e,tabValues:r}))throw new Error(`Can't select invalid tab value=${e}`);d(e),o(e),f(e)}),[o,f,r]),tabValues:r}}var v=s(2303);const g={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};function m(e){let{className:n,block:s,selectedValue:r,selectValue:l,tabValues:c}=e;const t=[],{blockElementScrollPositionUntilNextRender:o}=(0,d.a_)(),h=e=>{const n=e.currentTarget,s=t.indexOf(n),i=c[s].value;i!==r&&(o(n),l(i))},u=e=>{let n=null;switch(e.key){case"Enter":h(e);break;case"ArrowRight":{const s=t.indexOf(e.currentTarget)+1;n=t[s]??t[0];break}case"ArrowLeft":{const s=t.indexOf(e.currentTarget)-1;n=t[s]??t[t.length-1];break}}n?.focus()};return(0,i.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,a.A)("tabs",{"tabs--block":s},n),children:c.map((e=>{let{value:n,label:s,attributes:l}=e;return(0,i.jsx)("li",{role:"tab",tabIndex:r===n?0:-1,"aria-selected":r===n,ref:e=>t.push(e),onKeyDown:u,onClick:h,...l,className:(0,a.A)("tabs__item",g.tabItem,l?.className,{"tabs__item--active":r===n}),children:s??n},n)}))})}function k(e){let{lazy:n,children:s,selectedValue:r}=e;const a=(Array.isArray(s)?s:[s]).filter(Boolean);if(n){const e=a.find((e=>e.props.value===r));return e?(0,l.cloneElement)(e,{className:"margin-top--md"}):null}return(0,i.jsx)("div",{className:"margin-top--md",children:a.map(((e,n)=>(0,l.cloneElement)(e,{key:n,hidden:e.props.value!==r})))})}function y(e){const n=j(e);return(0,i.jsxs)("div",{className:(0,a.A)("tabs-container",g.tabList),children:[(0,i.jsx)(m,{...e,...n}),(0,i.jsx)(k,{...e,...n})]})}function N(e){const n=(0,v.A)();return(0,i.jsx)(y,{...e,children:f(e.children)},String(n))}const S={tabItem:"tabItem_Ymn6"};function w(e){let{children:n,hidden:s,className:r}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,a.A)(S.tabItem,r),hidden:s,children:n})}const I={sidebar_position:1,id:"intro",title:"Refined4s",slug:"/"},D="Refined4s",A={id:"intro",title:"Refined4s",description:"Build Status",source:"@site/../generated-docs/docs/intro.md",sourceDirName:".",slug:"/",permalink:"/docs/",draft:!1,unlisted:!1,tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1,id:"intro",title:"Refined4s",slug:"/"},sidebar:"docsSidebar",next:{title:"core module",permalink:"/docs/core/"}},V={},E=[{value:"Getting Started",id:"getting-started",level:2},{value:"refined4s-core",id:"refined4s-core",level:3},{value:"refined4s-cats",id:"refined4s-cats",level:3},{value:"refined4s-circe",id:"refined4s-circe",level:3},{value:"refined4s-pureconfig",id:"refined4s-pureconfig",level:3},{value:"refined4s-doobie-ce2",id:"refined4s-doobie-ce2",level:3},{value:"refined4s-doobie-ce3",id:"refined4s-doobie-ce3",level:3},{value:"refined4s-extras-render",id:"refined4s-extras-render",level:3},{value:"refined4s-tapir",id:"refined4s-tapir",level:3},{value:"All refined4s modules",id:"all-refined4s-modules",level:3},{value:"Why <code>refined4s</code>?",id:"why-refined4s",level:2}];function _(e){const n={a:"a",code:"code",h1:"h1",h2:"h2",h3:"h3",hr:"hr",img:"img",p:"p",pre:"pre",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",...(0,r.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(n.h1,{id:"refined4s",children:"Refined4s"}),"\n",(0,i.jsxs)(n.p,{children:[(0,i.jsx)(n.a,{href:"https://github.com/kevin-lee/refined4s/actions?workflow=Build-All",children:(0,i.jsx)(n.img,{src:"https://github.com/kevin-lee/refined4s/workflows/Build-All/badge.svg",alt:"Build Status"})}),"\n",(0,i.jsx)(n.a,{href:"https://github.com/kevin-lee/refined4s/actions?workflow=Release",children:(0,i.jsx)(n.img,{src:"https://github.com/kevin-lee/refined4s/workflows/Release/badge.svg",alt:"Release Status"})}),"\n",(0,i.jsx)(n.a,{href:"https://index.scala-lang.org/kevin-lee/refined4s",children:(0,i.jsx)(n.img,{src:"https://index.scala-lang.org/kevin-lee/refined4s/latest.svg",alt:"Latest version"})})]}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsx)(n.img,{alt:"Refined4s Logo",src:s(3338).A+"",width:"320",height:"320"})}),"\n",(0,i.jsx)(n.p,{children:"Newtypes and Refinement types for Scala 3"}),"\n",(0,i.jsxs)(n.table,{children:[(0,i.jsx)(n.thead,{children:(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.th,{style:{textAlign:"right"},children:"Project"}),(0,i.jsx)(n.th,{children:"Maven Central"})]})}),(0,i.jsxs)(n.tbody,{children:[(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{style:{textAlign:"right"},children:"refined4s-core"}),(0,i.jsx)(n.td,{children:(0,i.jsx)(n.a,{href:"https://search.maven.org/artifact/io.kevinlee/refined4s-core_3",children:(0,i.jsx)(n.img,{src:"https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-core_3/badge.svg",alt:"Maven Central"})})})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{style:{textAlign:"right"},children:"refined4s-cats"}),(0,i.jsx)(n.td,{children:(0,i.jsx)(n.a,{href:"https://search.maven.org/artifact/io.kevinlee/refined4s-cats_3",children:(0,i.jsx)(n.img,{src:"https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-cats_3/badge.svg",alt:"Maven Central"})})})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{style:{textAlign:"right"},children:"refined4s-circe"}),(0,i.jsx)(n.td,{children:(0,i.jsx)(n.a,{href:"https://search.maven.org/artifact/io.kevinlee/refined4s-circe_3",children:(0,i.jsx)(n.img,{src:"https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-circe_3/badge.svg",alt:"Maven Central"})})})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{style:{textAlign:"right"},children:"refined4s-pureconfig"}),(0,i.jsx)(n.td,{children:(0,i.jsx)(n.a,{href:"https://search.maven.org/artifact/io.kevinlee/refined4s-pureconfig_3",children:(0,i.jsx)(n.img,{src:"https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-pureconfig_3/badge.svg",alt:"Maven Central"})})})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{style:{textAlign:"right"},children:"refined4s-doobie-ce2"}),(0,i.jsx)(n.td,{children:(0,i.jsx)(n.a,{href:"https://search.maven.org/artifact/io.kevinlee/refined4s-doobie-ce2_3",children:(0,i.jsx)(n.img,{src:"https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-doobie-ce2_3/badge.svg",alt:"Maven Central"})})})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{style:{textAlign:"right"},children:"refined4s-doobie-ce3"}),(0,i.jsx)(n.td,{children:(0,i.jsx)(n.a,{href:"https://search.maven.org/artifact/io.kevinlee/refined4s-doobie-ce3_3",children:(0,i.jsx)(n.img,{src:"https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-doobie-ce3_3/badge.svg",alt:"Maven Central"})})})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{style:{textAlign:"right"},children:"refined4s-extras-render"}),(0,i.jsx)(n.td,{children:(0,i.jsx)(n.a,{href:"https://search.maven.org/artifact/io.kevinlee/refined4s-extras-render_3",children:(0,i.jsx)(n.img,{src:"https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-extras-render_3/badge.svg",alt:"Maven Central"})})})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{style:{textAlign:"right"},children:"refined4s-tapir"}),(0,i.jsx)(n.td,{children:(0,i.jsx)(n.a,{href:"https://search.maven.org/artifact/io.kevinlee/refined4s-tapir_3",children:(0,i.jsx)(n.img,{src:"https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-tapir_3/badge.svg",alt:"Maven Central"})})})]})]})]}),"\n",(0,i.jsx)(n.h2,{id:"getting-started",children:"Getting Started"}),"\n",(0,i.jsxs)(n.p,{children:["To get ",(0,i.jsx)(n.code,{children:"refined4s"})," for your project,"]}),"\n",(0,i.jsx)(n.h3,{id:"refined4s-core",children:"refined4s-core"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-core" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-core" % "0.15.0"\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %% "refined4s-core" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %%% "refined4s-core" % "0.15.0"\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-core:0.15.0"\n'})})})]}),"\n",(0,i.jsx)(n.h3,{id:"refined4s-cats",children:"refined4s-cats"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-cats" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-cats" % "0.15.0"\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %% "refined4s-cats" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %%% "refined4s-cats" % "0.15.0"\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-cats:0.15.0"\n'})})})]}),"\n",(0,i.jsx)(n.h3,{id:"refined4s-circe",children:"refined4s-circe"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-circe" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-circe" % "0.15.0"\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %% "refined4s-circe" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %%% "refined4s-circe" % "0.15.0"\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-circe:0.15.0"\n'})})})]}),"\n",(0,i.jsx)(n.h3,{id:"refined4s-pureconfig",children:"refined4s-pureconfig"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-pureconfig" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-pureconfig" % "0.15.0"\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %% "refined4s-pureconfig" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %%% "refined4s-pureconfig" % "0.15.0"\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-pureconfig:0.15.0"\n'})})})]}),"\n",(0,i.jsx)(n.h3,{id:"refined4s-doobie-ce2",children:"refined4s-doobie-ce2"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-doobie-ce2" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-doobie-ce2" % "0.15.0"\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %% "refined4s-doobie-ce2" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %%% "refined4s-doobie-ce2" % "0.15.0"\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-doobie-ce2:0.15.0"\n'})})})]}),"\n",(0,i.jsx)(n.h3,{id:"refined4s-doobie-ce3",children:"refined4s-doobie-ce3"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-doobie-ce3" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-doobie-ce3" % "0.15.0"\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %% "refined4s-doobie-ce3" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %%% "refined4s-doobie-ce3" % "0.15.0"\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-doobie-ce3:0.15.0"\n'})})})]}),"\n",(0,i.jsx)(n.hr,{}),"\n",(0,i.jsx)(n.h3,{id:"refined4s-extras-render",children:"refined4s-extras-render"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-extras-render" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-extras-render" % "0.15.0"\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %% "refined4s-extras-render" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %%% "refined4s-extras-render" % "0.15.0"\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-extras-render:0.15.0"\n'})})})]}),"\n",(0,i.jsx)(n.hr,{}),"\n",(0,i.jsx)(n.h3,{id:"refined4s-tapir",children:"refined4s-tapir"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-tapir" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-tapir" % "0.15.0"\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %% "refined4s-tapir" % "0.15.0"\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies += "io.kevinlee" %%% "refined4s-tapir" % "0.15.0"\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-tapir:0.15.0"\n'})})})]}),"\n",(0,i.jsx)(n.hr,{}),"\n",(0,i.jsx)(n.h3,{id:"all-refined4s-modules",children:"All refined4s modules"}),"\n",(0,i.jsxs)(N,{groupId:"refined4s",defaultValue:"refined4s-sbt",values:[{label:"sbt",value:"refined4s-sbt"},{label:"sbt (with libraryDependencies)",value:"refined4s-sbt-lib"},{label:"scala-cli",value:"refined4s-scala-cli"}],children:[(0,i.jsxs)(w,{value:"refined4s-sbt",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %% "refined4s-core" % "0.15.0",\n"io.kevinlee" %% "refined4s-cats" % "0.15.0",\n"io.kevinlee" %% "refined4s-circe" % "0.15.0",\n"io.kevinlee" %% "refined4s-pureconfig" % "0.15.0",\n"io.kevinlee" %% "refined4s-doobie-ce2" % "0.15.0", // Use either refined4s-doobie-ce2\n"io.kevinlee" %% "refined4s-doobie-ce3" % "0.15.0", // OR refined4s-doobie-ce3\n"io.kevinlee" %% "refined4s-extras-render" % "0.15.0",\n"io.kevinlee" %% "refined4s-tapir" % "0.15.0",\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'"io.kevinlee" %%% "refined4s-core" % "0.15.0",\n"io.kevinlee" %%% "refined4s-cats" % "0.15.0",\n"io.kevinlee" %%% "refined4s-circe" % "0.15.0",\n"io.kevinlee" %%% "refined4s-pureconfig" % "0.15.0",\n"io.kevinlee" %%% "refined4s-doobie-ce2" % "0.15.0", // Use either refined4s-doobie-ce2\n"io.kevinlee" %%% "refined4s-doobie-ce3" % "0.15.0", // OR refined4s-doobie-ce3\n"io.kevinlee" %%% "refined4s-extras-render" % "0.15.0",\n"io.kevinlee" %%% "refined4s-tapir" % "0.15.0",\n'})})]}),(0,i.jsxs)(w,{value:"refined4s-sbt-lib",children:[(0,i.jsxs)(n.p,{children:["In ",(0,i.jsx)(n.code,{children:"build.sbt"}),","]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies ++= Seq(\n  "io.kevinlee" %% "refined4s-core" % "0.15.0",\n  "io.kevinlee" %% "refined4s-cats" % "0.15.0",\n  "io.kevinlee" %% "refined4s-circe" % "0.15.0",\n  "io.kevinlee" %% "refined4s-pureconfig" % "0.15.0",\n  "io.kevinlee" %% "refined4s-doobie-ce2" % "0.15.0", // Use either refined4s-doobie-ce2\n  "io.kevinlee" %% "refined4s-doobie-ce3" % "0.15.0", // OR refined4s-doobie-ce3\n  "io.kevinlee" %% "refined4s-extras-render" % "0.15.0",\n  "io.kevinlee" %% "refined4s-tapir" % "0.15.0",\n)\n'})}),(0,i.jsx)(n.p,{children:"or for Scala.js"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'libraryDependencies ++= Seq(\n  "io.kevinlee" %%% "refined4s-core" % "0.15.0",\n  "io.kevinlee" %%% "refined4s-cats" % "0.15.0",\n  "io.kevinlee" %%% "refined4s-circe" % "0.15.0",\n  "io.kevinlee" %%% "refined4s-pureconfig" % "0.15.0",\n  "io.kevinlee" %%% "refined4s-doobie-ce2" % "0.15.0", // Use either refined4s-doobie-ce2\n  "io.kevinlee" %%% "refined4s-doobie-ce3" % "0.15.0", // OR refined4s-doobie-ce3\n  "io.kevinlee" %%% "refined4s-extras-render" % "0.15.0",\n  "io.kevinlee" %%% "refined4s-tapir" % "0.15.0",\n)\n'})})]}),(0,i.jsx)(w,{value:"refined4s-scala-cli",children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'//> using dep "io.kevinlee::refined4s-core:0.15.0"\n//> using dep "io.kevinlee::refined4s-cats:0.15.0"\n//> using dep "io.kevinlee::refined4s-circe:0.15.0"\n//> using dep "io.kevinlee::refined4s-pureconfig:0.15.0"\n//> using dep "io.kevinlee::refined4s-doobie-ce2:0.15.0" // Use either refined4s-doobie-ce2\n//> using dep "io.kevinlee::refined4s-doobie-ce3:0.15.0" // OR refined4s-doobie-ce3\n//> using dep "io.kevinlee::refined4s-extras-render:0.15.0"\n//> using dep "io.kevinlee::refined4s-tapir:0.15.0"\n'})})})]}),"\n",(0,i.jsxs)(n.h2,{id:"why-refined4s",children:["Why ",(0,i.jsx)(n.code,{children:"refined4s"}),"?"]}),"\n",(0,i.jsx)(n.p,{children:"Given the following methods"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'def hello(name: String): Unit = println(s"Hello $name")\ndef sendEmail(email: String): Unit = {\n  println(s"Sending email to [email address: $email]")\n  // ... send email\n}\n'})}),"\n",(0,i.jsx)(n.p,{children:"You can easily mess up method parameters like this."}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'val name = "Kevin"\n// name: String = "Kevin"\nval email = "blah@blah.blah"\n// email: String = "blah@blah.blah"\n\nhello(email)\n// Hello blah@blah.blah\nsendEmail(name)\n// Sending email to [email address: Kevin]\n'})}),"\n",(0,i.jsxs)(n.p,{children:["If you use ",(0,i.jsx)(n.code,{children:"refined4s"}),", you don't need to worry about that anymore."]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'import refined4s.*\n\ntype Name = Name.Type\n\nobject Name extends Newtype[String]\n\ntype Email = Email.Type\n\nobject Email extends Newtype[String]\n\ndef hello(name: Name): Unit = println(s"Hello ${name.value}")\ndef sendEmail(email: Email): Unit = {\n  println(s"Sending email to [email address: ${email.value}]")\n  // ... send email\n}\n'})}),"\n",(0,i.jsx)(n.p,{children:"You can easily mess up method parameters like this."}),"\n",(0,i.jsx)(n.p,{children:"If you pass the right types, it works."}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'val name = Name("Kevin")\n// name: Type = "Kevin"\nval email = Email("blah@blah.blah")\n// email: Type = "blah@blah.blah"\n\nhello(name)\n// Hello Kevin\nsendEmail(email)\n// Sending email to [email address: blah@blah.blah]\n'})}),"\n",(0,i.jsx)(n.p,{children:"If you don't, it does not compile."}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:"hello(email)\nsendEmail(name)\n// error:\n// Found:    (repl.MdocSession.MdocApp1.email : repl.MdocSession.MdocApp1.Email.Type)\n// Required: repl.MdocSession.MdocApp1.Name\n// hello(email)\n//       ^^^^^\n// error:\n// Found:    (repl.MdocSession.MdocApp1.name : repl.MdocSession.MdocApp1.Name.Type)\n// Required: repl.MdocSession.MdocApp1.Email\n// sendEmail(name)\n//           ^^^^\n// error: \n// Line is indented too far to the left, or a `}` is missing\n// error: \n// Line is indented too far to the left, or a `}` is missing\n// error: \n// Line is indented too far to the left, or a `}` is missing\n"})})]})}function M(e={}){const{wrapper:n}={...(0,r.R)(),...e.components};return n?(0,i.jsx)(n,{...e,children:(0,i.jsx)(_,{...e})}):_(e)}},3338:(e,n,s)=>{s.d(n,{A:()=>i});const i=s.p+"assets/images/refined4s-320x320-2483b0bb373fca5ed168465ebe10e97e.png"},8453:(e,n,s)=>{s.d(n,{R:()=>a,x:()=>d});var i=s(6540);const r={},l=i.createContext(r);function a(e){const n=i.useContext(l);return i.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function d(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(r):e.components||r:a(e.components),i.createElement(l.Provider,{value:n},e.children)}}}]);