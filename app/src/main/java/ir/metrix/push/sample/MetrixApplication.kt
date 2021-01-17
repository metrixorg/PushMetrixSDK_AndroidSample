package ir.metrix.push.sample

import android.app.Application
import android.util.Log
import ir.metrix.AttributionData
import ir.metrix.Metrix
import ir.metrix.OnAttributionChangeListener
import ir.metrix.UserIdListener
import ir.metrix.notification.MetrixNotification
import ir.metrix.notification.push.MetrixNotificationListener
import ir.metrix.notification.push.NotificationButtonData
import ir.metrix.notification.push.NotificationData

class MetrixApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // -------------------- Metrix ---------------------
        Metrix.setStore("GooglePlay");

        val userAttributes = mapOf("phoneNumber" to "09121111111")
        Metrix.addUserAttributes(userAttributes);

        Metrix.setUserIdListener(object: UserIdListener {
            override fun onUserIdReceived(userId: String) {
                Log.d(TAG, "onUserIdReceived: Metrix userId: $userId")
            }
        })

        Metrix.setOnAttributionChangedListener(object: OnAttributionChangeListener {
            override fun onAttributionChanged(attributionData: AttributionData) {
                Log.d(TAG, "onAttributionChanged: Attribution Status: ${attributionData.attributionStatus}")
            }
        })

        // -------------------- MetrixNotification ---------------------
        val sportTopic = "sport"
        MetrixNotification.subscribeToTopic(sportTopic) {
            Log.d(TAG, "Successfully subscribed to topic: $sportTopic")
        }

        MetrixNotification.setNotificationListener(object: MetrixNotificationListener {
            override fun onNotification(notificationData: NotificationData) {

            }

            override fun onNotificationButtonClick(notificationButtonData: NotificationButtonData, notificationData: NotificationData) {

            }

            override fun onNotificationClick(notificationData: NotificationData) {

            }

            override fun onCustomContentNotification(customContent: MutableMap<String, Any>) {

            }

            override fun onNotificationDismiss(notificationData: NotificationData) {

            }
        })
    }

    companion object {
        private const val TAG = "MetrixApplication"
    }
}