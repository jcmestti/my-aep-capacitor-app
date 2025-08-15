import { AdobeAep } from '../../src/index';

window.initializeAEP = async () => {
  console.log('[CLICK] Initialize AEP button clicked');
  try {
    const result = await AdobeAep.configure({ appId: 'INSERT_YOUR_ID_HERE' });
    console.log('✅ AEP configured:', result);
  } catch (error) {
    console.error('❌ Failed to configure AEP:', error);
  }
};
/*
window.sendScreenView = async () => {
  console.log('[CLICK] Send Screen View button clicked');
 
    const result = await AdobeAep.trackEvent({
      xdm: {
        eventType: 'user_interaction',
        timestamp: "2025-08-15T01:46:38Z",
        _telefonicavivo: {
          eventDetails: {
            screenName: 'creenName',
            event_name: 'event_name',
            audience_expression_1: 'audience_expression_1',
            item_list_name: 'item_list_name',
            item_name: 'item_name',
            item_variant: 'item_variant'
          },
          vivoAppUser: {
            vivoCustomerID: '5b51cf71221d3f5da3f2d58eb64adceda85c91c6d39689d8f2159c2d82129f89',
            msisdn: '406078d03983199dbc82b574e1b6dbf354acd01f4f3ffec17064ba8c16b16aag',
          },
        },
      },
    });
};
*/
window.trackEvent = async () => {
    AdobeAep.trackScreenView().then(() => {
    console.log('Screen view sent!');
  });
}

window.setConsentYes = async () => {
  console.log('[CLICK] Set Consent: YES button clicked');
  try {
    await AdobeAep.setConsent({ consent: 'y' });
    console.log('✅ Consent set to yes');
  } catch (error) {
    console.error('❌ Failed to set consent:', error);
  }
};

window.setConsentNo = async () => {
  console.log('[CLICK] Set Consent: NO button clicked');
  try {
    await AdobeAep.setConsent({ consent: 'n' });
    console.log('✅ Consent set to no');
  } catch (error) {
    console.error('❌ Failed to set consent:', error);
  }
};

window.startAssurance = async () => {
  console.log('[CLICK] Start Assurance button clicked');
  try {
    await AdobeAep.startAssurance({
      url: 'aepplugin://?adb_validation_sessionid=bc4c3e97-890a-4eeb-aa28-bb591670f3e3' // replace with your real session link
    });
    console.log('🧪 Adobe Assurance session started');
  } catch (error) {
    console.error('❌ Failed to start Assurance session:', error);
  }
};
