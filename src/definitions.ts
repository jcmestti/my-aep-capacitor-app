export interface AdobeAepPlugin {
  configure(options: { appId: string }): Promise<{ success: boolean }>;
  trackEvent(options: { xdm: Record<string, any> }): Promise<{ success: boolean }>;
}
