import {Component} from '@angular/core';

// noinspection HtmlUnknownTarget
@Component({
  selector: 'app-footer',
  template: `
    <footer id="footer"><p (click)="play()">&copy; 2025 Pan Sauce. No rights reserved.</p></footer>
    <audio id="music" src="/assets/music/Fluffy.flac" loop></audio>
  `,
  standalone: true,
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  private isPlaying = false;

  play() {
    let music = document.getElementById('music') as HTMLAudioElement;
    if(this.isPlaying) music.pause();
    else music.play();
    this.isPlaying = !this.isPlaying;
  }
}
