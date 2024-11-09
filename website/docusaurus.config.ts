import {themes as prismThemes} from 'prism-react-renderer';
import type {Config} from '@docusaurus/types';
import type * as Preset from '@docusaurus/preset-classic';

import LatestVersion from '@types/commonTypes';

const algoliaConfig = require('./algolia.config.json');
const googleAnalyticsConfig = require('./google-analytics.config.json');

// const lightCodeTheme = require('prism-react-renderer/themes/github');
// const darkCodeTheme = require('prism-react-renderer/themes/dracula');
const lightCodeTheme = prismThemes.nightOwlLight;
const darkCodeTheme = prismThemes.nightOwl;


const isEmptyObject = (obj: object) => Object.keys(obj).length === 0;

const isSearchable = !isEmptyObject(algoliaConfig);
const hasGoogleAnalytics = !isEmptyObject(googleAnalyticsConfig);

import LatestVersionImported from './latestVersion.json';
const latestVersionFound = LatestVersionImported as LatestVersion;

const config: Config = {
  title: 'Refined4s',
  tagline: 'Newtypes and Refinement types for Scala 3',
  favicon: 'img/favicon.png',

  // Set the production url of your site here
  url: 'https://refined4s.kevinly.dev',
  // Set the /<baseUrl>/ pathname under which your site is served
  // For GitHub pages deployment, it is often '/<projectName>/'
  baseUrl: '/',

  // GitHub pages deployment config.
  // If you aren't using GitHub pages, you don't need these.
  organizationName: 'kevin-lee', // Usually your GitHub org/user name.
  projectName: 'refined4s', // Usually your repo name.

  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',

  // Even if you don't use internationalization, you can use this field to set
  // useful metadata like html lang. For example, if your site is Chinese, you
  // may want to replace "en" with "zh-Hans".
  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          path: '../generated-docs/docs/',
          sidebarPath: require.resolve('./sidebars.ts'),
          "lastVersion": "current",
          "versions": {
            "v0": {
              "label": "v0.x.0",
              "path": "v0",
            },
            "current": {
              "label": `v${latestVersionFound.version}`,
            },
          }
        },
        blog: {
          showReadingTime: true,
          // Please change this to your repo.
          // Remove this to remove the "edit this page" links.
        },
        theme: {
          customCss: './src/css/custom.css',
        },
        ...(hasGoogleAnalytics ? {"gtag": googleAnalyticsConfig} : {}),
      } satisfies Preset.Options,
    ],
  ],

  themeConfig: {
    // Replace with your project's social card
    image: 'img/refined4s-social-card.jpg',
    docs: {
      sidebar: {
        hideable: true,
      },
    },
    navbar: {
      title: 'Refined4s',
      logo: {
        alt: 'Refined4s',
        src: 'img/refined4s-64x64.png',
      },
      items: [
        {
          type: 'docSidebar',
          sidebarId: 'docsSidebar',
          position: 'left',
          label: 'Docs',
        },
        {
          type: 'docsVersionDropdown',
          position: 'right',
          dropdownActiveClassDisabled: true,
          dropdownItemsAfter: [
            {
              to: '/versions',
              label: 'All versions',
            },
          ],
        },
        {
          href: 'https://github.com/kevin-lee/refined4s',
          label: 'GitHub',
          position: 'right',
        },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Docs',
          items: [
            {
              label: 'Docs',
              to: '/docs',
            },
          ],
        },
        {
          title: 'More',
          items: [
            {
              label: 'GitHub',
              href: 'https://github.com/kevin-lee/refined4s',
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} Refined4s. Built with Docusaurus.
      <div><a href="https://www.flaticon.com/free-icons/scala" title="scala icons">Scala</a>, <a href="https://www.flaticon.com/free-icons/shapes" title="shapes icons">Shapes</a> and <a href="https://www.flaticon.com/free-icons/shield" title="shield icons">Shield</a> icons created by Freepik - Flaticon</div>
      `,
    },
    prism: {
      theme: lightCodeTheme,
      darkTheme: darkCodeTheme,
      additionalLanguages: [
        'bash',
        'diff',
        'json',
        'java',
        'scala',
      ],
    },
  } satisfies Preset.ThemeConfig,
};

if (isSearchable) {
  config['themeConfig']['algolia'] = algoliaConfig;
}

export default config;
