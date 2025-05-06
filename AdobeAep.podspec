require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name = 'AdobeAep'
  s.version = package['version']
  s.summary = package['description']
  s.license = package['license']
  s.homepage = package['repository']['url']
  s.author = package['author']
  s.source = { :git => package['repository']['url'], :tag => s.version.to_s }
  s.source_files = 'ios/Sources/**/*.{swift,h,m,c,cc,mm,cpp}'
  s.ios.deployment_target = '13.0'
  s.dependency 'Capacitor'
  s.swift_version = '5.1'
  
  s.dependency 'AEPCore', '~> 5.0'
  s.dependency 'AEPAssurance', '~> 5.0'
  s.dependency 'AEPEdge', '~> 5.0'
  s.dependency 'AEPEdgeIdentity', '~> 5.0'
  s.dependency 'AEPTarget', '~> 5.0'
  s.dependency 'AEPIdentity', '~> 5.0'
  s.dependency 'AEPSignal', '~> 5.0'
  s.dependency 'AEPLifecycle', '~> 5.0'
  s.dependency 'AEPUserProfile', '~> 5.0'
  s.dependency 'AEPAudience', '~> 5.0'
  s.dependency 'AEPEdgeConsent', '~> 5.0'
end
