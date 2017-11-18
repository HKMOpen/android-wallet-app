# IOTA Android App
This project is the official Android app for IOTA. Responsive material design app supports devices running Android 5.0+.

[![Build Status](https://travis-ci.org/iotaledger/android-wallet-app.svg?branch=master)](https://travis-ci.org/iotaledger/android-wallet-app)

<h2>Features</h2>

General:
- [x] Light client
- [x] Responsive material design
- [x] Secure and ready to start
- [x] Connect to your own node

Wallet:
- [x] Send and receive IOTA token
- [x] Full QR Code support
- [x] Address generating
- [x] Transaction and address history

Tangle Explorer:
- [x] Live Tangle explorer with full search function

Neighbors:
- [x] Add/remove neighbors and view the current state of them

Miscellaneous:
- [x] Current IOTA balance in different currencies such as BTC, USD, EUR, YEN
- [x] Report issues or add a feature request directly to github
- [x] Seed generator
- [x] Seed password protection


<h2>How to build</h2>


```bash
./gradlew clean build
```
### Wallet:
- [x] Your private keys are stored on your device and are never communicated with any server
- [x] Sweep funds from private keys
- [x] Standard BIP44 avoids address reuse
- [x] Put Samourai into stealth mode to hide it on the device
- [x] May use separate PIN code for stealth mode launch
- [x] Enable remote SMS commands to regain access to your funds if you lose your phone
- [x] Obtain new phone number if SIM card swapped out following loss or theft
- [x] Custom fiat currency prices from popular exchanges
- [x] Segwit/UASF block explorer support
- [x] BIP39 passphrase enforced on new wallets
- [x] Sign messages using utxo addresses (including P2SH-P2WPKH Segwit utxos)
- [x] Read, validate, sweep Coinkite OpenDime
- [x] Detect private keys remaining in clipboard

### Security:
- [x] 5-to-8 digit PIN protected access
- [x] PIN entry grid may be scrambled
- [x] AES-256 encryption of internal metadata
- [x] Exportable backup encrypted using BIP39 passphrase
- [x] Route outgoing transactions via your own trusted node (JSON-RPC)
- [x] Connect via your preferred VPN
- [x] Connect via Tor (Socks5 proxy using Orbot)
- [x] Samourai pushTx over Tor
- [x] PoW (proof-of-work) check when using trusted node
- [x] Real-time alert if your wallet is being "dusted"

### Stealth addressing/BIP47 payment channels:
- [x] BIP47 "Reusable Payment Codes" support
- [x] Paymentcode.io lookup of BIP47 payment codes
- [x] BIP47 payment codes scannable via BIP21
- [x] Sign messages using BIP47 notification address

### Transactions:
- [x] Full Segwit support (P2SH-P2WPKH)
- [x] Sweep P2SH-P2WPKH private keys
- [x] RBF (replace-by-fee) detection for incoming transactions
- [x] BIP69 deterministic sorting of input/outputs to prevent the wallet from leaving a discernible block chain fingerprint
- [x] BIP126 spending for obfuscating your outgoing transactions
- [x] Ricochet spend (spend using several hops)
- [x] Select fee provider (21.co or bitcoind)
- [x] Dynamic fee support guarantees fast confirm times
- [x] Display up-to-date miners' fees
- [x] Display UTXO list (optionally display private keys of UTXOs, redeem scripts of P2SH-P2WPKH)
- [x] Any UTXO can be flagged as "unspendable"
- [x] Allow custom fee (in addition to proposed low-normal-high fees) when spending
- [x] CPFP (child-pays-for-parent) for unconfirmed received transactions
- [x] CPFP (child-pays-for-parent) for unconfirmed sent transactions
- [x] Opt-in RBF (replace-by-fee)
- [x] Display transaction as hex/QR code for alternative means of transmission
- [x] push any signed tx (scanned or pasted in hex format)

<h2>Download</h2>

<a href='https://play.google.com/store/apps/details?id=org.iota.wallet'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width="323" height="125"/></a></a>

<h2>Supporting the project</h2>
If you like our work please feel free to contribute by posting a bug report or push a pull request.
Also a donation is very welcome too.

- [x] Bitcoin: `1MyCJP3yZtSJ3bMVEoQRPSY3D6Ev7CTvzo`
- [x] IOTA: `TBH9CSFWUHACJSWGA9XDDMNPJ9USPRLJ9FCHDEYDYGOWPQTQUWXMUBCUKTFJRESNBHGJOISFJOLXTLZOBRLLGVTROD`

### Thank you for your support and we will bring more amazing libraries to your productive works. We are accepting bitcoin by the address as below. Please scan the QR code to start
![wallet](http://s32.postimg.org/sdd1oio1t/qrwallet.jpg)


