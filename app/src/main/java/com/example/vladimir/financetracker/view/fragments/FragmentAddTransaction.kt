package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.vladimir.financetracker.Constants
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.createId
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_transaction.*

class FragmentAddTransaction() : Fragment() {

    lateinit var mViewModel: FinanceTrackerViewModel
    val transactionTypes = arrayOf("Трата", "Доход")
    val currency = arrayOf("Рубли", "Доллары")
    val category = arrayOf("Еда", "Одежда", "Развлечения", "Другое")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(FinanceTrackerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {

        fragment_transaction_category.adapter = object : ArrayAdapter<String>(context,
                R.layout.spinner_item, category) {
        }

        fragment_transaction_currency.adapter = object : ArrayAdapter<String>(context,
                R.layout.spinner_item, currency) {
        }

    }


    private fun initComponentsListeners() {

        fragment_wallets_ok.setOnClickListener {
            if (fragment_transaction_name.text.toString().isNotBlank()
                    && fragment_transaction_value.text.toString().isNotBlank()
                    && fragment_transaction_date.text.toString().isNotBlank()) {

                mViewModel.addTransaction(Transaction(createId(),
                        fragment_transaction_name.text.toString(),
                        fragment_transaction_expendture.isChecked,
                        fragment_transaction_currency.selectedItem.toString(),
                        fragment_transaction_category.selectedItem.toString(),
                        fragment_transaction_value.text.toString().toDouble(),
                        fragment_transaction_date.text.toString(),
                ""))
                fragmentManager?.popBackStackImmediate()
            }
        }

        fragment_transaction_toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
    }
}