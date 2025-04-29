import { registerPlugin } from '@capacitor/core';

import type { AdobeAepPlugin } from './definitions';

const AdobeAep  = registerPlugin<AdobeAepPlugin>('AdobeAep', {
  web: () => import('./web').then((m) => new m.AdobeAepWeb()),
});

export * from './definitions';
export { AdobeAep };

