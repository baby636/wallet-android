package com.mycelium.wallet.activity.fio.requests

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycelium.wallet.R
import com.mycelium.wallet.activity.TransactionDetailsActivity
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.ACCOUNT
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.AMOUNT
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.CONVERTED_AMOUNT
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.DATE
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.FEE
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.FROM
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.MEMO
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.TO
import com.mycelium.wallet.activity.fio.requests.ApproveFioRequestActivity.Companion.TXID
import com.mycelium.wallet.activity.util.toStringWithUnit
import com.mycelium.wapi.wallet.coins.Value
import kotlinx.android.synthetic.main.fio_send_request_info.tvAmount
import kotlinx.android.synthetic.main.fio_send_request_status_activity.*
import java.text.DateFormat
import java.util.*


class ApproveFioRequestSuccessActivity : AppCompatActivity() {
    companion object {
        fun start(activity: Activity, amount: Value,
                  convertedAmount: String,
                  fee: Value,
                  date: Long,
                  from: String,
                  to: String, memo: String,
                  txid: ByteArray,
                  accountId: UUID) {
            with(Intent(activity, ApproveFioRequestSuccessActivity::class.java)) {
                putExtra(AMOUNT, amount)
                putExtra(CONVERTED_AMOUNT, convertedAmount)
                putExtra(FEE, fee)
                putExtra(DATE, date)
                putExtra(FROM, from)
                putExtra(TO, to)
                putExtra(MEMO, memo)
                putExtra(TXID, txid)
                putExtra(ACCOUNT, accountId)
                activity.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fio_send_request_status_activity)

        supportActionBar?.run {
            title = "Success"
        }
        tvAmount.text = (intent.getSerializableExtra(AMOUNT) as Value).toStringWithUnit()
        tvConvertedAmount.text = " ~ ${intent.getStringExtra(CONVERTED_AMOUNT)}"
        tvMinerFee.text = (intent.getSerializableExtra(FEE) as Value).toStringWithUnit()
        tvDate.text = getDateString(intent.getLongExtra(DATE, 0))
        tvFrom.text = intent.getStringExtra(FROM)
        tvTo.text = intent.getStringExtra(TO)
        tvMemo.text = intent.getStringExtra(MEMO)
        tvTxDetailsLink.setOnClickListener {
            val intent: Intent = Intent(this, TransactionDetailsActivity::class.java)
                    .putExtra(TransactionDetailsActivity.EXTRA_TXID, intent.getByteArrayExtra(TXID))
                    .putExtra(TransactionDetailsActivity.ACCOUNT_ID, intent.getSerializableExtra(ACCOUNT))
            startActivity(intent)
        }
    }

    private fun getDateString(timestamp: Long): String {
        val date = Date(timestamp)
        val locale = resources.configuration.locale

        val dayFormat = DateFormat.getDateInstance(DateFormat.LONG, locale)
        val dateString = dayFormat.format(date)

        val hourFormat = DateFormat.getTimeInstance(DateFormat.LONG, locale)
        val timeString = hourFormat.format(date)

        return dateString + timeString
    }
}