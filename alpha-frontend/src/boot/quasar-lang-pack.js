// import something here
import { Quasar } from 'quasar'
// "async" is optional
export default async () => {
  const langIso = 'ko-kr' // ... some logic to determine it (use Cookies Plugin?)
  try {
    await import(`quasar/lang/${langIso}`)
      .then(lang => {
        Quasar.lang.set(lang.default)
      })
  } catch (err) {
    // Requested Quasar Language Pack does not exist,
    // let's not break the app, so catching error
  }
}
