import { WebPlugin } from '@capacitor/core';

import type { AdobeAepPlugin } from './definitions';

export class AdobeAepWeb extends WebPlugin implements AdobeAepPlugin {
  async configure(_: { appId: string }): Promise<{ success: boolean }> {
    console.warn('[AdobeAepWeb] configure() is not supported on web.');
    return { success: false };
  }

  async trackEvent(_: { xdm: Record<string, any> }): Promise<{ success: boolean }> {
    console.warn('[AdobeAepWeb] trackEvent() is not supported on web.');
    return { success: false };
  }
}
