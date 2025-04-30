import Foundation
import AEPCore
import AEPEdge
import AEPEdgeIdentity
import AEPEdgeConsent
import AEPLifecycle
import AEPSignal
import AEPTarget
import AEPAudience
import AEPUserProfile
import AEPAssurance

@objc public class AdobeAep: NSObject {
    
    @objc public func configure(appId: String, completion: @escaping () -> Void) {
        MobileCore.setLogLevel(.debug)
        MobileCore.registerExtensions([
            Lifecycle.self,
            Signal.self,
            Identity.self,
            Edge.self,
            AEPEdgeIdentity.Identity.self,
            AEPEdgeConsent.Consent.self,
            UserProfile.self,
            Audience.self,
            Assurance.self,
            Target.self
        ]) {
            MobileCore.configureWith(appId: appId)
            MobileCore.lifecycleStart(additionalContextData: ["contextDataKey": "contextDataVal"])
            completion()
        }
    }

    @objc public func trackEvent(xdm: [String: Any], completion: @escaping () -> Void) {
        let event = ExperienceEvent(xdm: xdm)
        Edge.sendEvent(experienceEvent: event) { _ in
            completion()
        }
    }
}
