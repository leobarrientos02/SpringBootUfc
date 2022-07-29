import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class FrontEndFunctionsService {

  constructor(private router: Router) { }

  public goToFightPage(id: any){
    this.router.navigate([`fight/${id}`]);
  }

  public goToFighterPage(id: any){
    this.router.navigate([`fighter/${id}`]);
  }

  public goToRefereePage(refereeId: any){
    this.router.navigate([`referee/${refereeId}`]);
  }

  public fightTypeFormatter(fightType: String){
    if(fightType == "CHAMPIONSHIP"){
      return "Championship";
    }else if(fightType == "THREE_ROUNDS"){
      return "3 Rounds";
    }else{
      return "5 Rounds";
    }
  }

  public resultFormatter(result: String){
    if(result == "DECISION"){
      return "Decision";
    }else if(result == "SPLIT_DECISION"){
      return "Split Decision";
    }else if(result == "KO"){
      return "Knockout";
    }else if(result == "UNANIMOUS_DECISION"){
      return "Unanimous Decision";
    }else if(result == "TKO"){
      return "Technical Knockout";
    }else if(result == "DRAW"){
      return "Draw";
    }else if(result == "Doctor Stoppage"){
      return "Doctor Stoppage";
    }else{
      return "TBA";
    }
  }

  // DOM Functions
  public openForm(formType: any, page: String){
    const shadow = document.getElementById('shadow');
    shadow?.classList.add('showShadow');
    const form = document.getElementById(`${formType}${page}`);
    form?.classList.add('showForm');
  }

  public closeForm(formType: any, page: String){
    const shadow = document.getElementById('shadow');
    shadow?.classList.remove('showShadow');
    const form = document.getElementById(`${formType}${page}`);
    form?.classList.remove('showForm');
  }

  public closeAnyForm( page: String){
    const shadow = document.getElementById('shadow');
    shadow?.classList.remove('showShadow');
    const form = document.getElementById(`update${page}`);
    form?.classList.remove('showForm');
    const form2 = document.getElementById(`delete${page}`);
    form2?.classList.remove('showForm');
    const form3 = document.getElementById(`add${page}`);
    form3?.classList.remove('showForm');
  }
}
