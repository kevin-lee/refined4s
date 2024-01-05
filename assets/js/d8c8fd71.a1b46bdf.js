"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[80],{8099:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>a,contentTitle:()=>d,default:()=>p,frontMatter:()=>r,metadata:()=>s,toc:()=>l});var i=t(5893),o=t(1151);const r={id:"doobie",title:"doobie module"},d=void 0,s={id:"doobie/doobie",title:"doobie module",description:"Import",source:"@site/../generated-docs/docs/doobie/doobie.md",sourceDirName:"doobie",slug:"/doobie/",permalink:"/docs/doobie/",draft:!1,unlisted:!1,tags:[],version:"current",frontMatter:{id:"doobie",title:"doobie module"},sidebar:"docsSidebar",previous:{title:"pureconfig module",permalink:"/docs/pureconfig/"}},a={},l=[{value:"Import",id:"import",level:2},{value:"Use Drived Instances for Pre-defined Types",id:"use-drived-instances-for-pre-defined-types",level:2},{value:"With Explicit Pre-defined Doobie Support",id:"with-explicit-pre-defined-doobie-support",level:2},{value:"With <code>deriving</code> Method",id:"with-deriving-method",level:2}];function c(e){const n={a:"a",admonition:"admonition",code:"code",h2:"h2",li:"li",p:"p",pre:"pre",strong:"strong",ul:"ul",...(0,o.a)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(n.h2,{id:"import",children:"Import"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:"import refined4s.modules.doobie.derivation.types.all.given\n"})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:"import refined4s.modules.doobie.derivation.*\n"})}),"\n",(0,i.jsx)(n.h2,{id:"use-drived-instances-for-pre-defined-types",children:"Use Drived Instances for Pre-defined Types"}),"\n",(0,i.jsxs)(n.p,{children:["To use ",(0,i.jsx)(n.code,{children:"Newtype"}),", ",(0,i.jsx)(n.code,{children:"Refined"})," and ",(0,i.jsx)(n.code,{children:"InlinedRefined"})," with Doobie by having ",(0,i.jsx)(n.code,{children:"Get"})," and ",(0,i.jsx)(n.code,{children:"Put"})," (or ",(0,i.jsx)(n.code,{children:"Read"})," and ",(0,i.jsx)(n.code,{children:"Write"})," from ",(0,i.jsx)(n.code,{children:"Get"})," and ",(0,i.jsx)(n.code,{children:"Put"}),") type-class instances derived from the actual values, you can simply use"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:"import refined4s.modules.doobie.derivation.types.all.given\n"})}),"\n",(0,i.jsx)(n.admonition,{title:"NOTE",type:"warning",children:(0,i.jsxs)(n.p,{children:["This works only when the actual type already has ",(0,i.jsx)(n.code,{children:"Get"})," and ",(0,i.jsx)(n.code,{children:"Put"}),"."]})}),"\n",(0,i.jsx)(n.admonition,{type:"info",children:(0,i.jsxs)(n.p,{children:["Using ",(0,i.jsx)(n.code,{children:"refined4s.modules.doobie.derivation.types.all.given"})," is required only when ",(0,i.jsx)(n.code,{children:"Get"})," and/or ",(0,i.jsx)(n.code,{children:"Put"})," is required for the pre-defined types.",(0,i.jsx)("br",{}),"\nSo if you want your ",(0,i.jsx)(n.code,{children:"Newtype"})," or ",(0,i.jsx)(n.code,{children:"Refined"})," or ",(0,i.jsx)(n.code,{children:"InlinedRefined"})," to have ",(0,i.jsx)(n.code,{children:"Get"})," and ",(0,i.jsx)(n.code,{children:"Put"})," instances,",(0,i.jsx)("br",{}),"\nyou can use ",(0,i.jsx)(n.a,{href:"#with-explicit-pre-defined-doobie-support",children:"pre-defined traits for doobie"})," or the ",(0,i.jsxs)(n.a,{href:"#with-deriving-method",children:[(0,i.jsx)(n.code,{children:"deriving"})," method"]})," instead.",(0,i.jsx)("br",{})]})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:"import cats.effect.*\n\nimport refined4s.*\nimport refined4s.types.all.*\n\nimport doobie.*\nimport doobie.syntax.all.*\n\ndef insertOrUpdate[F[*]](fragment: Fragment)(transactor: Transactor[F])(\n  using bracket: Bracket[F, Throwable]\n): F[Int] =\n  fragment\n    .update\n    .run\n    .transact(transactor)\n"})}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsxs)(n.strong,{children:["With ",(0,i.jsx)(n.code,{children:"derivation.types.all"}),","]})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",metastring:"{1}",children:'import refined4s.modules.doobie.derivation.types.all.given\n\nfinal case class Person(name: NonEmptyString)\n\nval name = NonEmptyString("Tony Stark")\n\ninsertOrUpdate[F](\n  sql"""\n    INSERT INTO db_tools_test.example (value) VALUES ($name)\n  """\n)(transactor)\n\n// You don\'t have to do use .value like\n// insertOrUpdate[F](\n// sql"""\n//     INSERT INTO db_tools_test.example (value) VALUES ($name.value)\n//   """\n// )(transactor)\n'})}),"\n",(0,i.jsx)(n.h2,{id:"with-explicit-pre-defined-doobie-support",children:"With Explicit Pre-defined Doobie Support"}),"\n",(0,i.jsxs)(n.p,{children:["There are the following pre-defined traits to support doobie's ",(0,i.jsx)(n.code,{children:"Get"})," and ",(0,i.jsx)(n.code,{children:"Put"})," (or ",(0,i.jsx)(n.code,{children:"Codec"}),")."]}),"\n",(0,i.jsxs)(n.ul,{children:["\n",(0,i.jsx)(n.li,{children:(0,i.jsx)(n.code,{children:"refined4s.modules.doobie.derivation.DoobiePut"})}),"\n",(0,i.jsx)(n.li,{children:(0,i.jsx)(n.code,{children:"refined4s.modules.doobie.derivation.DoobieNewtypeGet"})}),"\n",(0,i.jsx)(n.li,{children:(0,i.jsx)(n.code,{children:"refined4s.modules.doobie.derivation.DoobieRefinedGet"})}),"\n"]}),"\n",(0,i.jsx)(n.admonition,{title:"NOTE",type:"warning",children:(0,i.jsxs)(n.p,{children:["This works only when the actual type already has ",(0,i.jsx)(n.code,{children:"Get"})," and/or ",(0,i.jsx)(n.code,{children:"Put"}),"."]})}),"\n",(0,i.jsx)(n.p,{children:"e.g.)"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",metastring:"{8,15,18}",children:'import cats.effect.*\n\nimport refined4s.*\nimport refined4s.types.all.*\nimport refined4s.modules.doobie.derivation.*\n\ntype NotEmptyStr = NotEmptyStr.Type\nobject NotEmptyStr extends Refined[String], DoobieRefinedGetPut[String] {\n  inline def invalidReason(a: String): String = "non-empty String"\n\n  inline def predicate(a: String): Boolean = a != ""\n}\n\ntype Name = Name.Type\nobject Name extends Newtype[NotEmptyStr], DoobieNewtypeGetPut[NotEmptyStr]\n\ntype Id = Id.Type\nobject Id extends Newtype[Long], DoobieNewtypeGetPut[Long]\n\nfinal case class Person(id: Id, name: Name)\n\nimport doobie.*\nimport doobie.syntax.all.*\n\ndef insertOrUpdate[F[*]](fragment: Fragment)(transactor: Transactor[F])(\n  using bracket: Bracket[F, Throwable]\n): F[Int] =\n  fragment\n    .update\n    .run\n    .transact(transactor)\n'})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'val person = Person(Id(1), Name(NotEmptyStr("Kevin")))\n\ninsertOrUpdate[F](\n  sql"""\n    INSERT INTO db_tools_test.example (id, name) VALUES (${person.id}, ${person.name})\n  """\n)(transactor)\n\n// You don\'t have to use .value or .value.value like\n// insertOrUpdate[F](\n//   sql"""\n//     INSERT INTO db_tools_test.example (id, name) VALUES (${person.id.value}, ${person.name.value.value})\n//   """\n// )(transactor)\n\n'})}),"\n",(0,i.jsxs)(n.h2,{id:"with-deriving-method",children:["With ",(0,i.jsx)(n.code,{children:"deriving"})," Method"]}),"\n",(0,i.jsxs)(n.p,{children:["If you want to have explicit ",(0,i.jsx)(n.code,{children:"Get"})," and ",(0,i.jsx)(n.code,{children:"Put"})," type-class instances in your ",(0,i.jsx)(n.code,{children:"Newtype"})," or ",(0,i.jsx)(n.code,{children:"Refined"})," or ",(0,i.jsx)(n.code,{children:"InlinedRefined"}),", you can use the ",(0,i.jsx)(n.code,{children:"deriving"})," method."]}),"\n",(0,i.jsx)(n.admonition,{title:"NOTE",type:"warning",children:(0,i.jsxs)(n.p,{children:["This works only when the actual type already has ",(0,i.jsx)(n.code,{children:"Get"})," and ",(0,i.jsx)(n.code,{children:"Put"}),"."]})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",metastring:"{9-10,19-20,25-26}",children:'import cats.effect.*\n\nimport refined4s.*\n\nimport doobie.*\n\ntype Name = Name.Type\nobject Name extends Newtype[NotEmptyStr] {\n  given getName: Get[Name] = deriving[Get]\n  given putName: Put[Name] = deriving[Put]\n}\n\ntype NotEmptyStr = NotEmptyStr.Type\nobject NotEmptyStr extends Refined[String] {\n  inline def invalidReason(a: String): String = "non-empty String"\n\n  inline def predicate(a: String): Boolean = a != ""\n\n  given getNotEmptyStr: Get[NotEmptyStr] = Get[String].temap(NotEmptyStr.from)\n  given putNotEmptyStr: Put[NotEmptyStr] = deriving[Put]\n}\n\ntype Id = Id.Type\nobject Id extends Newtype[Long] {\n  given getId: Get[Id] = deriving[Get]\n  given putId: Put[Id] = deriving[Put]\n}\n\nfinal case class Person(id: Id, name: Name)\n\nimport doobie.syntax.all.*\n\ndef insertOrUpdate[F[*]](fragment: Fragment)(transactor: Transactor[F])(\n  using bracket: Bracket[F, Throwable]\n): F[Int] =\n  fragment\n    .update\n    .run\n    .transact(transactor)\n'})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-scala",children:'val person = Person(Id(1), Name(NotEmptyStr("Kevin")))\n\ninsertOrUpdate[F](\n  sql"""\n    INSERT INTO db_tools_test.example (id, name) VALUES (${person.id}, ${person.name})\n  """\n)(transactor)\n\n// You don\'t have to use .value or .value.value like\n// insertOrUpdate[F](\n//   sql"""\n//     INSERT INTO db_tools_test.example (id, name) VALUES (${person.id.value}, ${person.name.value.value})\n//   """\n// )(transactor)\n\n'})})]})}function p(e={}){const{wrapper:n}={...(0,o.a)(),...e.components};return n?(0,i.jsx)(n,{...e,children:(0,i.jsx)(c,{...e})}):c(e)}},1151:(e,n,t)=>{t.d(n,{Z:()=>s,a:()=>d});var i=t(7294);const o={},r=i.createContext(o);function d(e){const n=i.useContext(r);return i.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function s(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(o):e.components||o:d(e.components),i.createElement(r.Provider,{value:n},e.children)}}}]);