import Capacitor

@objc(AdobeAepPlugin)
public class AdobeAepPlugin: CAPPlugin {
    private let implementation = AdobeAep()

    @objc func configure(_ call: CAPPluginCall) {
        guard let appId = call.getString("appId"), !appId.isEmpty else {
            call.reject("appId is required")
            return
        }

        implementation.configure(appId: appId) {
            call.resolve(["success": true])
        }
    }

    @objc func trackEvent(_ call: CAPPluginCall) {
        guard let xdm = call.getObject("xdm") as? [String: Any] else {
            call.reject("xdm must be an object")
            return
        }

        implementation.trackEvent(xdm: xdm) {
            call.resolve(["success": true])
        }
    }
}
