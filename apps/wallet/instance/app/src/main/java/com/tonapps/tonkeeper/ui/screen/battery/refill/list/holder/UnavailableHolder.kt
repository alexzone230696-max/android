package com.tonapps.tonkeeper.ui.screen.battery.refill.list.holder

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.tonapps.tonkeeper.ui.screen.battery.refill.list.Item
import com.tonapps.tonkeeperx.R
import com.tonapps.wallet.localization.Localization

class UnavailableHolder(
    parent: ViewGroup,
) : Holder<Item.Unavailable>(parent, R.layout.view_battery_unavailable) {

    private val textView = itemView.findViewById<AppCompatTextView>(R.id.text)

    override fun onBind(item: Item.Unavailable) {
        textView.text = getString(Localization.charging_unavailable)
    }
}