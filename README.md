# GoofyCat

> Tap the cat: a joke Android app that swaps a still image for a looping cat video and sound

[![License](https://img.shields.io/badge/license-MIT-2ea44f?style=flat-square)](LICENSE)
[![Stack](https://img.shields.io/badge/stack-Android%20%7C%20Java-000?style=flat-square)](#tech-stack)

One screen, one cat. The app shows a cat picture, and tapping anywhere hides it, starts
a looping video of the same cat, and plays a cat sound on repeat. Tapping again stops
both and brings the picture back.

This is a joke and a coursework experiment, which the repository description (`wat`)
already admits. It is one activity with no backend, no data, and no product ambitions.

## What it does

- Draws `res/drawable/catimg.png` centered on a white screen, with a `Hello World`
  label left over from the template at the top.
- On tap, hides the image, shows the `VideoView`, and starts `res/raw/catwebm.mp4` on
  loop together with `res/raw/catsound.mp3` on loop.
- On the next tap, stops playback, releases the `MediaPlayer`, and shows the image
  again.
- Pauses the video in `onPause` and resumes it in `onResume`.

## Tech stack

| Layer | Choice |
|-------|--------|
| Language | Java 11 source/target |
| Platform | minSdk 24, targetSdk 35, compileSdk 35 |
| Build | Gradle 8.11.1 wrapper, AGP 8.9.0, version catalog in `gradle/libs.versions.toml` |
| UI | AppCompat 1.7.0, Material 1.12.0, one `VideoView` in a `RelativeLayout` |
| Media | Bundled MP3 and MP4 in `app/src/main/res/raw` |

## Run it

1. Open Android Studio, then **File > Open** and select this repository folder.
2. Let Gradle sync, choose the `app` run configuration, and press **Run**.
3. Tap the screen and enjoy the meow.

Or build the debug APK from a terminal:

```bash
sh gradlew :app:assembleDebug
```

The wrapper script is committed without the executable bit, so call it through `sh`
instead of `./gradlew`.

## Notes and limitations

- Pausing the app stops the video but not the sound loop, because `onPause` only
  touches the `VideoView`. Tap the screen to stop the audio.
- The manifest declares `INTERNET` and `READ_EXTERNAL_STORAGE`, but both clips ship in
  `res/raw`, so neither permission is used by the current code.
- The `Hello World` text view and the `catimg` drawable are part of the joke layout,
  not leftovers to fix.
- There are no tests beyond the generated `ExampleUnitTest` and
  `ExampleInstrumentedTest`.
- The repository is a single commit experiment, not a maintained app.

## License

MIT, see [LICENSE](LICENSE). Copyright (c) 2026 Mai.
