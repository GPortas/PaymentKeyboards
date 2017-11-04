 [ ![Download](https://api.bintray.com/packages/gportas/maven/payment-keyboards/images/download.svg) ](https://bintray.com/gportas/maven/payment-keyboards/_latestVersion)

# Payment Keyboards

Android Library that provides animated and customizable intelligent payment keyboards. This library is full developed in Kotlin language.

## Add gradle dependency

Add the following dependency to your build:gradle app file, then sync the project

- `compile 'com.gportas:payment-keyboards:0.9.2'`

## Some code examples

Instantiate an expiration date keyboard:

```kotlin
val myExpirationDateKeyboard = ExpirationDateKeyboardFragment(20, R.color.keyboardPrimaryColor, R.color.keyboardSecondaryColor, R.color.keyboardPrimaryTextColor, R.color.keyboardSecondaryTextColor)
```

Add a listener to get the date entered on the keyboard:

```kotlin
myExpirationDateKeyboard.setDateChangedListener(object : DataChangedListener() {
	override fun onDataChanged(data: String) {
    	  //Do whatever you want
  }
})
```

Instantiate a credit card numeric keyboard:

```kotlin
val myCreditCardKeyboard = CreditCardNumberKeyboardFragment(R.color.keyboardPrimaryColor, R.color.keyboardSecondaryColor, R.color.keyboardPrimaryTextColor, R.color.keyboardSecondaryTextColor)
```

Add a listener to get the number entered on the keyboard:

```kotlin
myCreditCardKeyboard.setCreditCardNumberChangedListener(object : DataChangedListener() {
    override fun onDataChanged(data: String) {
        //Do whatever you want
    }
})
```

Now we will use a CreditCardTypeListener to get the credit card type by the entered number. In this case we are going to throw a toast message if the credit card is a Mastercard card:

```kotlin
myCreditCardKeyboard.setCreditCardTypeListener(object : CreditCardTypeListener(){
    override fun onCardRecognized(cardNumber: String, type: String) {
      if(CreditCardTypeListener.TYPE_MASTERCARD == type){
          Toast.makeText(applicationContext, "Mastercard", Toast.LENGTH_SHORT).show()
      }
    }
})
```

Implement IKeyboardManager interface in your activity to manage your keyboard opening and closing:

```kotlin
class MyActivity : IKeyboardManager, AppCompatActivity() { 
    override var isKeyboardOpened: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        /.../
    }

    override fun openKeyboard(activity: AppCompatActivity, keyboard: BaseKeyboardFragment, frameLayoutResId: Int) {
        super.openKeyboard(activity, keyboard, frameLayoutResId)
    }

    override fun openKeyboardWithSlideUpAnimation(activity: AppCompatActivity, keyboard: BaseKeyboardFragment, frameLayoutResId: Int) {
        super.openKeyboardWithSlideUpAnimation(activity, keyboard, frameLayoutResId)
    }

    override fun hideKeyboard(activity: AppCompatActivity, frameLayoutResId: Int) {
        super.hideKeyboard(activity, frameLayoutResId)
    }
}
```

## Versions

Check [Bintray](https://bintray.com/gportas/maven/payment-keyboards) to be aware of the latest version and see the full history of versions.

## Authors

* **Guillermo Portas** - [GPortas](https://github.com/gportas)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
