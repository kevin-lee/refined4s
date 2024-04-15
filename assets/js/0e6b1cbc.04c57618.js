"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[705],{7653:(e,n,i)=>{i.r(n),i.d(n,{assets:()=>s,contentTitle:()=>t,default:()=>c,frontMatter:()=>o,metadata:()=>a,toc:()=>l});var d=i(5893),r=i(1151);const o={id:"pureconfig",title:"pureconfig module"},t=void 0,a={id:"pureconfig/pureconfig",title:"pureconfig module",description:"Import",source:"@site/../generated-docs/docs/pureconfig/pureconfig.md",sourceDirName:"pureconfig",slug:"/pureconfig/",permalink:"/docs/pureconfig/",draft:!1,unlisted:!1,tags:[],version:"current",frontMatter:{id:"pureconfig",title:"pureconfig module"},sidebar:"docsSidebar",previous:{title:"circe module",permalink:"/docs/circe/"},next:{title:"doobie module",permalink:"/docs/doobie/"}},s={},l=[{value:"Import",id:"import",level:2},{value:"Use Drived Instances for Pre-defined Types",id:"use-drived-instances-for-pre-defined-types",level:2},{value:"With Explicit Pre-defined Pureconfig Support",id:"with-explicit-pre-defined-pureconfig-support",level:2},{value:"With <code>deriving</code> Method",id:"with-deriving-method",level:2}];function p(e){const n={a:"a",admonition:"admonition",code:"code",h2:"h2",li:"li",p:"p",pre:"pre",strong:"strong",ul:"ul",...(0,r.a)(),...e.components};return(0,d.jsxs)(d.Fragment,{children:[(0,d.jsx)(n.h2,{id:"import",children:"Import"}),"\n",(0,d.jsx)(n.pre,{children:(0,d.jsx)(n.code,{className:"language-scala",children:"import refined4s.modules.pureconfig.derivation.types.all.given\n"})}),"\n",(0,d.jsx)(n.pre,{children:(0,d.jsx)(n.code,{className:"language-scala",children:"import refined4s.modules.pureconfig.derivation.*\n"})}),"\n",(0,d.jsx)(n.h2,{id:"use-drived-instances-for-pre-defined-types",children:"Use Drived Instances for Pre-defined Types"}),"\n",(0,d.jsxs)(n.p,{children:["To make ",(0,d.jsx)(n.code,{children:"Newtype"}),", ",(0,d.jsx)(n.code,{children:"Refined"})," and ",(0,d.jsx)(n.code,{children:"InlinedRefined"})," have ",(0,d.jsx)(n.code,{children:"ConfigReader"})," and ",(0,d.jsx)(n.code,{children:"ConfigWriter"})," type-class instances derived from the actual values, you can simply use"]}),"\n",(0,d.jsx)(n.pre,{children:(0,d.jsx)(n.code,{className:"language-scala",children:"import refined4s.modules.pureconfig.derivation.types.all.given\n"})}),"\n",(0,d.jsx)(n.admonition,{title:"NOTE",type:"warning",children:(0,d.jsxs)(n.p,{children:["This works only when the actual type already has ",(0,d.jsx)(n.code,{children:"ConfigReader"})," and ",(0,d.jsx)(n.code,{children:"ConfigWriter"}),"."]})}),"\n",(0,d.jsx)(n.admonition,{type:"info",children:(0,d.jsxs)(n.p,{children:["Using ",(0,d.jsx)(n.code,{children:"refined4s.modules.pureconfig.derivation.types.all.given"})," is required only when ",(0,d.jsx)(n.code,{children:"ConfigReader"})," and/or ",(0,d.jsx)(n.code,{children:"ConfigWriter"})," is required for the pre-defined types.",(0,d.jsx)("br",{}),"\nSo if you want your ",(0,d.jsx)(n.code,{children:"Newtype"})," or ",(0,d.jsx)(n.code,{children:"Refined"})," or ",(0,d.jsx)(n.code,{children:"InlinedRefined"})," to have ",(0,d.jsx)(n.code,{children:"ConfigReader"})," and ",(0,d.jsx)(n.code,{children:"ConfigWriter"})," instances,",(0,d.jsx)("br",{}),"\nyou can use ",(0,d.jsx)(n.a,{href:"#with-explicit-pre-defined-pureconfig-support",children:"pre-defined traits for pureconfig"})," or the ",(0,d.jsxs)(n.a,{href:"#with-deriving-method",children:[(0,d.jsx)(n.code,{children:"deriving"})," method"]})," instead.",(0,d.jsx)("br",{})]})}),"\n",(0,d.jsx)(n.pre,{children:(0,d.jsx)(n.code,{className:"language-scala",children:"import refined4s.*\nimport refined4s.types.all.*\n\nimport com.typesafe.config.*\nimport pureconfig.generic.derivation.default.*\nimport pureconfig.*\n\nimport scala.jdk.CollectionConverters.*\n"})}),"\n",(0,d.jsx)(n.p,{children:(0,d.jsxs)(n.strong,{children:["With ",(0,d.jsx)(n.code,{children:"derivation.types.all"}),","]})}),"\n",(0,d.jsx)(n.pre,{children:(0,d.jsx)(n.code,{className:"language-scala",metastring:"{1}",children:'import refined4s.modules.pureconfig.derivation.types.all.given\n\nfinal case class NewtypeApiConfig(api: NewtypeApiConfig.Api) derives ConfigReader\nobject NewtypeApiConfig {\n  \n  final case class Api(\n    id: PosLong,\n    baseUri: Uri,\n    endpointPath: NonEmptyString,\n    additionalId: PosLong,\n  ) derives ConfigReader\n\n}\n\nval confString =\n  raw"""api {\n       |  id = 123\n       |  base-uri = "https://localhost:8080"\n       |  endpoint-path = "/v1/blah/blah"\n       |  additional-id = 999\n       |}\n       |""".stripMargin\n// confString: String = """api {\n//   id = 123\n//   base-uri = "https://localhost:8080"\n//   endpoint-path = "/v1/blah/blah"\n//   additional-id = 999\n// }\n// """\n\nConfigSource.string(confString).load[NewtypeApiConfig]\n// res1: Either[ConfigReaderFailures, NewtypeApiConfig] = Right(\n//   value = NewtypeApiConfig(\n//     api = Api(\n//       id = 123L,\n//       baseUri = "https://localhost:8080",\n//       endpointPath = "/v1/blah/blah",\n//       additionalId = 999L\n//     )\n//   )\n// )\n'})}),"\n",(0,d.jsx)(n.h2,{id:"with-explicit-pre-defined-pureconfig-support",children:"With Explicit Pre-defined Pureconfig Support"}),"\n",(0,d.jsxs)(n.p,{children:["There are the following pre-defined traits to support pureconfig ",(0,d.jsx)(n.code,{children:"ConfigReader"})," and ",(0,d.jsx)(n.code,{children:"ConfigWriter"}),"."]}),"\n",(0,d.jsxs)(n.ul,{children:["\n",(0,d.jsx)(n.li,{children:(0,d.jsx)(n.code,{children:"refined4s.modules.pureconfig.derivation.PureconfigConfigWriter"})}),"\n",(0,d.jsx)(n.li,{children:(0,d.jsx)(n.code,{children:"refined4s.modules.pureconfig.derivation.PureconfigNewtypeConfigReader"})}),"\n",(0,d.jsx)(n.li,{children:(0,d.jsx)(n.code,{children:"refined4s.modules.pureconfig.derivation.PureconfigRefinedConfigReader"})}),"\n"]}),"\n",(0,d.jsx)(n.admonition,{title:"NOTE",type:"warning",children:(0,d.jsxs)(n.p,{children:["This works only when the actual type already has  ",(0,d.jsx)(n.code,{children:"ConfigReader"})," and ",(0,d.jsx)(n.code,{children:"ConfigWriter"}),"."]})}),"\n",(0,d.jsx)(n.pre,{children:(0,d.jsx)(n.code,{className:"language-scala",metastring:"{24,37,40,48}",children:'import refined4s.*\nimport refined4s.types.all.*\nimport refined4s.modules.pureconfig.derivation.*\nimport refined4s.modules.pureconfig.derivation.types.all.given\n\nimport com.typesafe.config.*\nimport pureconfig.generic.derivation.default.*\nimport pureconfig.*\n\nimport scala.jdk.CollectionConverters.*\n\nfinal case class NewtypeApiConfig(api: NewtypeApiConfig.Api) derives ConfigReader\nobject NewtypeApiConfig {\n  \n  final case class Api(\n    id: Api.Id,\n    baseUri: Api.NewtypeBaseUri,\n    endpointPath: Api.RefinedEndpointPath,\n    additionalId: Api.InlinedRefinedNewtypeId,\n  ) derives ConfigReader\n  object Api {\n\n    type Id = Id.Type\n    object Id extends InlinedRefined[Long], PureconfigRefinedConfigReader[Long] {\n\n      override inline val inlinedExpectedValue = "a positive Long"\n\n      override inline def invalidReason(a: Long): String =\n        "It must be a positive Long"\n\n      override inline def predicate(a: Long): Boolean = a > 0L\n\n      override inline def inlinedPredicate(inline a: Long): Boolean = a > 0L\n    }\n\n    type NewtypeBaseUri = NewtypeBaseUri.Type\n    object NewtypeBaseUri extends Newtype[Uri], PureconfigNewtypeConfigReader[Uri]\n\n    type RefinedEndpointPath = RefinedEndpointPath.Type\n    object RefinedEndpointPath extends Refined[String], PureconfigRefinedConfigReader[String] {\n      override inline def invalidReason(a: String): String =\n        "It must be a non-empty String"\n\n      override inline def predicate(a: String): Boolean = a != ""\n    }\n\n    type InlinedRefinedNewtypeId = InlinedRefinedNewtypeId.Type\n    object InlinedRefinedNewtypeId extends Newtype[PosLong], PureconfigNewtypeConfigReader[PosLong]\n  }\n\n}\n\nval confString =\n  raw"""api {\n       |  id = 123\n       |  base-uri = "https://localhost:8080"\n       |  endpoint-path = "/v1/blah/blah"\n       |  additional-id = 999\n       |}\n       |""".stripMargin\n// confString: String = """api {\n//   id = 123\n//   base-uri = "https://localhost:8080"\n//   endpoint-path = "/v1/blah/blah"\n//   additional-id = 999\n// }\n// """\n\nConfigSource.string(confString).load[NewtypeApiConfig]\n// res3: Either[ConfigReaderFailures, NewtypeApiConfig] = Right(\n//   value = NewtypeApiConfig(\n//     api = Api(\n//       id = 123L,\n//       baseUri = "https://localhost:8080",\n//       endpointPath = "/v1/blah/blah",\n//       additionalId = 999L\n//     )\n//   )\n// )\n'})}),"\n",(0,d.jsxs)(n.h2,{id:"with-deriving-method",children:["With ",(0,d.jsx)(n.code,{children:"deriving"})," Method"]}),"\n",(0,d.jsxs)(n.p,{children:["If you want to have explicit ",(0,d.jsx)(n.code,{children:"ConfigReader"})," and ",(0,d.jsx)(n.code,{children:"ConfigWriter"})," type-class instances in your ",(0,d.jsx)(n.code,{children:"Newtype"})," or ",(0,d.jsx)(n.code,{children:"Refined"})," or ",(0,d.jsx)(n.code,{children:"InlinedRefined"}),", you can use the ",(0,d.jsx)(n.code,{children:"deriving"})," method."]}),"\n",(0,d.jsx)(n.admonition,{title:"NOTE",type:"warning",children:(0,d.jsxs)(n.p,{children:["This works only when the actual type already has ",(0,d.jsx)(n.code,{children:"ConfigReader"})," and ",(0,d.jsx)(n.code,{children:"ConfigWriter"}),"."]})}),"\n",(0,d.jsx)(n.pre,{children:(0,d.jsx)(n.code,{className:"language-scala",metastring:"{35,40,50,55}",children:'import refined4s.*\nimport refined4s.types.all.*\nimport refined4s.modules.pureconfig.derivation.*\nimport refined4s.modules.pureconfig.derivation.types.all.given\n\nimport com.typesafe.config.*\nimport pureconfig.generic.derivation.default.*\nimport pureconfig.*\n\nimport scala.jdk.CollectionConverters.*\n\nfinal case class NewtypeApiConfig(api: NewtypeApiConfig.Api) derives ConfigReader\nobject NewtypeApiConfig {\n  \n  final case class Api(\n    id: Api.Id,\n    baseUri: Api.NewtypeBaseUri,\n    endpointPath: Api.RefinedEndpointPath,\n    additionalId: Api.InlinedRefinedNewtypeId,\n  ) derives ConfigReader\n  object Api {\n\n    type Id = Id.Type\n    object Id extends InlinedRefined[Long] {\n\n      override inline val inlinedExpectedValue = "a positive Long"\n\n      override inline def invalidReason(a: Long): String =\n        "It must be a positive Long"\n\n      override inline def predicate(a: Long): Boolean = a > 0L\n\n      override inline def inlinedPredicate(inline a: Long): Boolean = a > 0L\n      \n      given configReaderId: ConfigReader[Id] = deriving[ConfigReader]\n    }\n\n    type NewtypeBaseUri = NewtypeBaseUri.Type\n    object NewtypeBaseUri extends Newtype[Uri] {\n      given configReaderNewtypeBaseUri: ConfigReader[NewtypeBaseUri] = deriving[ConfigReader]\n    }\n\n    type RefinedEndpointPath = RefinedEndpointPath.Type\n    object RefinedEndpointPath extends Refined[String] {\n      override inline def invalidReason(a: String): String =\n        "It must be a non-empty String"\n\n      override inline def predicate(a: String): Boolean = a != ""\n      \n      given configReaderRefinedEndpointPath: ConfigReader[RefinedEndpointPath] = deriving[ConfigReader]\n    }\n\n    type InlinedRefinedNewtypeId = InlinedRefinedNewtypeId.Type\n    object InlinedRefinedNewtypeId extends Newtype[PosLong] {\n      given configReaderInlinedRefinedNewtypeId: ConfigReader[InlinedRefinedNewtypeId] = deriving[ConfigReader]\n    }\n  }\n\n}\n\nval confString =\n  raw"""api {\n       |  id = 123\n       |  base-uri = "https://localhost:8080"\n       |  endpoint-path = "/v1/blah/blah"\n       |  additional-id = 999\n       |}\n       |""".stripMargin\n// confString: String = """api {\n//   id = 123\n//   base-uri = "https://localhost:8080"\n//   endpoint-path = "/v1/blah/blah"\n//   additional-id = 999\n// }\n// """\n\nConfigSource.string(confString).load[NewtypeApiConfig]\n// res5: Either[ConfigReaderFailures, NewtypeApiConfig] = Right(\n//   value = NewtypeApiConfig(\n//     api = Api(\n//       id = 123L,\n//       baseUri = "https://localhost:8080",\n//       endpointPath = "/v1/blah/blah",\n//       additionalId = 999L\n//     )\n//   )\n// )\n'})})]})}function c(e={}){const{wrapper:n}={...(0,r.a)(),...e.components};return n?(0,d.jsx)(n,{...e,children:(0,d.jsx)(p,{...e})}):p(e)}},1151:(e,n,i)=>{i.d(n,{Z:()=>a,a:()=>t});var d=i(7294);const r={},o=d.createContext(r);function t(e){const n=d.useContext(o);return d.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function a(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(r):e.components||r:t(e.components),d.createElement(o.Provider,{value:n},e.children)}}}]);