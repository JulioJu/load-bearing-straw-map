import { IUser } from '@/shared/model/user.model';

import { UsageBatiment } from '@/shared/model/enumerations/usage-batiment.model';
import { TaillesBottes } from '@/shared/model/enumerations/tailles-bottes.model';
import { Cereale } from '@/shared/model/enumerations/cereale.model';
import { YesNoPartial } from '@/shared/model/enumerations/yes-no-partial.model';
import { IntegBaie } from '@/shared/model/enumerations/integ-baie.model';
import { MateriauSb } from '@/shared/model/enumerations/materiau-sb.model';
import { RevetInt } from '@/shared/model/enumerations/revet-int.model';
import { RevetExt } from '@/shared/model/enumerations/revet-ext.model';
export interface IBatiments {
  id?: number;
  latitude?: number;
  longitude?: number;
  nom?: string | null;
  techniqueSecondaire?: boolean | null;
  usage?: UsageBatiment | null;
  cout?: number | null;
  surface?: number | null;
  niveaux?: number | null;
  travauxNeuf?: boolean | null;
  travauxExtension?: boolean | null;
  travauxRenov?: boolean | null;
  travauxIte?: boolean | null;
  travauxIti?: boolean | null;
  constructionDebut?: Date | null;
  constructionFin?: Date | null;
  bottesTaille?: TaillesBottes | null;
  bottesDensite?: number | null;
  bottesCereale?: Cereale | null;
  distanceAppro?: number | null;
  autoconstruction?: YesNoPartial | null;
  participatif?: YesNoPartial | null;
  integBaie?: IntegBaie | null;
  structSuppl?: boolean | null;
  noteCalcul?: boolean | null;
  materiauSb?: MateriauSb | null;
  revetInt?: RevetInt | null;
  revetExt?: RevetExt | null;
  concepteur?: string | null;
  realisateur?: string | null;
  description?: string | null;
  contactNom?: string | null;
  contactMail?: string | null;
  contactPhone?: string | null;
  codePostal?: string | null;
  creator?: IUser | null;
}

export class Batiments implements IBatiments {
  constructor(
    public id?: number,
    public latitude?: number,
    public longitude?: number,
    public nom?: string | null,
    public techniqueSecondaire?: boolean | null,
    public usage?: UsageBatiment | null,
    public cout?: number | null,
    public surface?: number | null,
    public niveaux?: number | null,
    public travauxNeuf?: boolean | null,
    public travauxExtension?: boolean | null,
    public travauxRenov?: boolean | null,
    public travauxIte?: boolean | null,
    public travauxIti?: boolean | null,
    public constructionDebut?: Date | null,
    public constructionFin?: Date | null,
    public bottesTaille?: TaillesBottes | null,
    public bottesDensite?: number | null,
    public bottesCereale?: Cereale | null,
    public distanceAppro?: number | null,
    public autoconstruction?: YesNoPartial | null,
    public participatif?: YesNoPartial | null,
    public integBaie?: IntegBaie | null,
    public structSuppl?: boolean | null,
    public noteCalcul?: boolean | null,
    public materiauSb?: MateriauSb | null,
    public revetInt?: RevetInt | null,
    public revetExt?: RevetExt | null,
    public concepteur?: string | null,
    public realisateur?: string | null,
    public description?: string | null,
    public contactNom?: string | null,
    public contactMail?: string | null,
    public contactPhone?: string | null,
    public codePostal?: string | null,
    public creator?: IUser | null
  ) {
    this.techniqueSecondaire = this.techniqueSecondaire ?? false;
    this.travauxNeuf = this.travauxNeuf ?? false;
    this.travauxExtension = this.travauxExtension ?? false;
    this.travauxRenov = this.travauxRenov ?? false;
    this.travauxIte = this.travauxIte ?? false;
    this.travauxIti = this.travauxIti ?? false;
    this.structSuppl = this.structSuppl ?? false;
    this.noteCalcul = this.noteCalcul ?? false;
  }
}
