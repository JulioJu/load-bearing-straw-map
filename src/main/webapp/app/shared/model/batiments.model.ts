import { TaillesBottes } from '@/shared/model/enumerations/tailles-bottes.model';
import { YesNoPartial } from '@/shared/model/enumerations/yes-no-partial.model';
import { UsageBatiment } from '@/shared/model/enumerations/usage-batiment.model';
import { Cereale } from '@/shared/model/enumerations/cereale.model';
import { RevetInt } from '@/shared/model/enumerations/revet-int.model';
import { RevetExt } from '@/shared/model/enumerations/revet-ext.model';
import { IntegBaie } from '@/shared/model/enumerations/integ-baie.model';
import { MateriauSb } from '@/shared/model/enumerations/materiau-sb.model';
export interface IBatiments {
  id?: number;
  latitude?: number;
  longitude?: number;
  nom?: string | null;
  contactNom?: string | null;
  contactMail?: string | null;
  contactPhone?: string | null;
  constructionDebut?: Date | null;
  constructionFin?: Date | null;
  surface?: number | null;
  cout?: number | null;
  bottesTaille?: TaillesBottes | null;
  autoconstruction?: YesNoPartial | null;
  concepteur?: string | null;
  realisateur?: string | null;
  participatif?: YesNoPartial | null;
  usage?: UsageBatiment | null;
  noteCalcul?: boolean | null;
  travauxNeuf?: boolean | null;
  travauxExtension?: boolean | null;
  travauxRenov?: boolean | null;
  travauxIte?: boolean | null;
  travauxIti?: boolean | null;
  niveaux?: number | null;
  bottesDensite?: number | null;
  distanceAppro?: number | null;
  bottesCereale?: Cereale | null;
  structSuppl?: boolean | null;
  revetInt?: RevetInt | null;
  revetExt?: RevetExt | null;
  techniqueSecondaire?: boolean | null;
  codePostal?: string | null;
  integBaie?: IntegBaie | null;
  materiauSb?: MateriauSb | null;
  description?: string | null;
}

export class Batiments implements IBatiments {
  constructor(
    public id?: number,
    public latitude?: number,
    public longitude?: number,
    public nom?: string | null,
    public contactNom?: string | null,
    public contactMail?: string | null,
    public contactPhone?: string | null,
    public constructionDebut?: Date | null,
    public constructionFin?: Date | null,
    public surface?: number | null,
    public cout?: number | null,
    public bottesTaille?: TaillesBottes | null,
    public autoconstruction?: YesNoPartial | null,
    public concepteur?: string | null,
    public realisateur?: string | null,
    public participatif?: YesNoPartial | null,
    public usage?: UsageBatiment | null,
    public noteCalcul?: boolean | null,
    public travauxNeuf?: boolean | null,
    public travauxExtension?: boolean | null,
    public travauxRenov?: boolean | null,
    public travauxIte?: boolean | null,
    public travauxIti?: boolean | null,
    public niveaux?: number | null,
    public bottesDensite?: number | null,
    public distanceAppro?: number | null,
    public bottesCereale?: Cereale | null,
    public structSuppl?: boolean | null,
    public revetInt?: RevetInt | null,
    public revetExt?: RevetExt | null,
    public techniqueSecondaire?: boolean | null,
    public codePostal?: string | null,
    public integBaie?: IntegBaie | null,
    public materiauSb?: MateriauSb | null,
    public description?: string | null
  ) {
    this.noteCalcul = this.noteCalcul ?? false;
    this.travauxNeuf = this.travauxNeuf ?? false;
    this.travauxExtension = this.travauxExtension ?? false;
    this.travauxRenov = this.travauxRenov ?? false;
    this.travauxIte = this.travauxIte ?? false;
    this.travauxIti = this.travauxIti ?? false;
    this.structSuppl = this.structSuppl ?? false;
    this.techniqueSecondaire = this.techniqueSecondaire ?? false;
  }
}
