import clsx from 'clsx';
import type {ReactNode} from 'react';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

type FeatureItem = {
  title: string;
  Svg: React.ComponentType<React.ComponentProps<'svg'>>;
  description: ReactNode;
};

const FeatureList: FeatureItem[] = [
  {
    title: 'Newtypes',
    Png: require('@site/static/img/shapes.png').default,
    description: (
      <>
        Refined4s provides an easy way to create newtypes for the existing types.
        So params like&nbsp;<br/>
        <code>(Int, String, String, Int)</code> can be<br/><code>(Id, Name, Description, Count)</code>
      </>
    ),
  },
  {
    title: 'Refinement Types',
    Png: require('@site/static/img/encrypted.png').default,
    description: (
      <>
        Refined4s also provides an easy way to create refinement types.
      </>
    ),
  },
  {
    title: 'For Scala 3',
    Svg: require('@site/static/img/scala.svg').default,
    description: (
      <>
        Refined4s is developed to support newtypes and refinement types for Scala 3.
      </>
    ),
  },
];

function FeatureSvg({title, Svg, description}: FeatureItem) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

function FeaturePng({Png, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <img src={Png} className={styles.featurePng} alt={title} />
      </div>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures(): JSX.Element {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            props.hasOwnProperty('Svg') ?
              <FeatureSvg key={idx} {...props} /> :
              <FeaturePng key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
