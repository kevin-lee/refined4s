/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

import React from 'react';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Link from '@docusaurus/Link';
import Layout from '@theme/Layout';
import Heading from '@theme/Heading';

import {
  useVersions,
  useLatestVersion,
  type Version,
} from '@docusaurus/plugin-content-docs/client';

import { ArchivedVersion, LatestVersion } from '@types/commonTypes';

import VersionsArchived from './versionsArchived.json';

import LatestVersionImported from '../../latestVersion.json';
const latestVersionFound = LatestVersionImported as LatestVersion;

export default function Version(): React.JSX.Element {

  const {siteConfig} = useDocusaurusContext();
  const versions = useVersions();
  const latestVersion = useLatestVersion();
  console.log(`latestVersion: ${JSON.stringify(latestVersion)}`);
  const currentVersion = versions.find((version) => version.name === 'current');
  const pastVersions = versions.filter(
    (version) => version !== latestVersion && version.name !== 'current',
  )
  .concat(VersionsArchived as ArchivedVersion[])
  .sort((a, b) => {
    // console.log(`a: ${JSON.stringify(a)}, b: ${JSON.stringify(b)}`);
    if (!a.name.includes(".") || !b.name.includes(".")) {
      if (a.name.includes("v")) {
        const aVersion = parseInt(a.name.substring(1).split(".")[0]);
        if (b.name.includes("v")) {
          const bVersion = parseInt(b.name.substring(1).split(".")[0]);
          return bVersion - aVersion;
        } else {
          const bVersion = parseInt(b.name.split(".")[0]);
          return bVersion - aVersion;
        }
      } else {
        const aVersion = parseInt(a.name.split(".")[0]);
        if (b.name.includes("v")) {
          const bVersion = parseInt(b.name.substring(1).split(".")[0]);
          return bVersion - aVersion;
        } else {
          const bVersion = parseInt(b.name.split(".")[0]);
          return bVersion - aVersion;
        }
      }
    }
    const [aMajor, aMinor, aPatchAndMore] = a.name.split(".");
    const [aPatch] = aPatchAndMore.split("-");
    const [bMajor, bMinor, bPatchAndMore] = b.name.split(".");
    const [bPatch] = bPatchAndMore.split("-");

    const a1 = parseInt(aMajor);
    const a2 = parseInt(aMinor);
    const a3 = parseInt(aPatch);

    const b1 = parseInt(bMajor);
    const b2 = parseInt(bMinor);
    const b3 = parseInt(bPatch);

    if (a1 > b1) {
      return -1;
    } else if (a1 === b1) {
      if (a2 > b2) {
        return -1;
      } else if (a2 === b2) {
        if (a3 > b3) {
          return -1;
        } else if (a3 === b3) {
          return 0;
        } else {
          return 1;
        }
      } else {
        return 1;
      }
    } else {
      return 1;
    }
  });
  console.log(`pastVersions: ${JSON.stringify(pastVersions)}`);
  // const stableVersion = pastVersions.shift();
  const stableVersion = currentVersion;
  const repoUrl = `https://github.com/${siteConfig.organizationName}/${siteConfig.projectName}`;

  const docLink = (path?: string) => path ? <Link to={path}>Documentation</Link> : <span>&nbsp;</span>;

  const releaseLink = (version: Version | ArchivedVersion) => version.label !== "v0.x.0" ?
      <a href={`${repoUrl}/releases/tag/v${version.name}`}>Release Notes</a> :
      <a href={`${repoUrl}/releases/tag/v0.19.0`}>Release Notes</a>;

  const spaces = (howMany: number) => <span dangerouslySetInnerHTML={{__html: "&nbsp;".repeat(howMany)}} />;

  return (
    <Layout
      title="Versions"
      description="Effectie Versions page listing all documented site versions">
      <main className="container margin-vert--lg">
        <h1>Effectie documentation versions</h1>

        {stableVersion && (
          <div className="margin-bottom--lg">
            <h3 id="next">Current version (Stable)</h3>
            <p>
              Here you can find the documentation for current released version.
            </p>
            <table>
              <tbody>
              <tr>
                <th>{latestVersionFound.version}</th>
                <td>
                  <Link to={stableVersion.path}>Documentation</Link>
                </td>
                <td>
                  <a href={`${repoUrl}/releases/tag/v${latestVersionFound.version}`}>
                    Release Notes
                  </a>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        )}
        {/*
        <div className="margin-bottom--lg">
          <h3 id="latest">Next version (Unreleased)</h3>
          <p>
            Here you can find the documentation for work-in-process unreleased
            version.
          </p>
          <table>
            <tbody>
            <tr>
              <th>{latestVersion.label}</th>
              <td>
                <Link to={latestVersion.path}>Documentation</Link>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        */}

        {pastVersions.length > 0 && (
          <div className="margin-bottom--lg">
            <h3 id="archive">Past versions (Not maintained anymore)</h3>
            <p>
              Here you can find documentation for previous versions of
              Effectie.
            </p>
            <table>
              <tbody>
              {pastVersions.map((version) => (
                <tr key={version.name}>
                  <th>{version.label}</th>
                  <td>
                    {docLink(version.path)}
                  </td>
                  <td>
                    {releaseLink(version)}
                  </td>
                </tr>
              ))}
              </tbody>
            </table>
          </div>
        )}

      </main>
    </Layout>
  );
}

export default Version;