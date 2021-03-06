import { IUser } from '@/shared/model/user.model';

import { UsageBatiment } from '@/shared/model/enumerations/usage-batiment.model';
import { TaillesBottes } from '@/shared/model/enumerations/tailles-bottes.model';
import { Cereale } from '@/shared/model/enumerations/cereale.model';
import { YesNoPartial } from '@/shared/model/enumerations/yes-no-partial.model';
import { StructureComplementaire } from '@/shared/model/enumerations/structure-complementaire.model';
import { IntegBaie } from '@/shared/model/enumerations/integ-baie.model';
import { SupportAncrage } from '@/shared/model/enumerations/support-ancrage.model';
import { RevetInt } from '@/shared/model/enumerations/revet-int.model';
import { RevetExt } from '@/shared/model/enumerations/revet-ext.model';
export interface IBatiment {
  id?: number;
  latitude?: number;
  longitude?: number;
  nomBatiment?: string | null;
  photoPrincipaleContentType?: string | null;
  photoPrincipale?: string | null;
  photoPrincipaleLegende?: string | null;
  photoPrincipaleDescription?: string | null;
  photo1ContentType?: string | null;
  photo1?: string | null;
  photo1Legende?: string | null;
  photo1Description?: string | null;
  photo2ContentType?: string | null;
  photo2?: string | null;
  photo2Legende?: string | null;
  photo2Description?: string | null;
  photo3ContentType?: string | null;
  photo3?: string | null;
  photo3Legende?: string | null;
  photo3Description?: string | null;
  photo4ContentType?: string | null;
  photo4?: string | null;
  photo4Legende?: string | null;
  photo4Description?: string | null;
  photo5ContentType?: string | null;
  photo5?: string | null;
  photo5Legende?: string | null;
  photo5Description?: string | null;
  usageBatiment?: UsageBatiment | null;
  usageBatimentInfos?: string | null;
  cout?: number | null;
  surfacePlancher?: number | null;
  niveaux?: number | null;
  travauxNeuf?: boolean | null;
  travauxExtension?: boolean | null;
  travauxRenov?: boolean | null;
  travauxIte?: boolean | null;
  travauxIti?: boolean | null;
  constructionDebut?: Date | null;
  constructionFin?: Date | null;
  bottesTaille?: TaillesBottes | null;
  bottesTailleInfos?: string | null;
  bottesDensite?: number | null;
  bottesCereale?: Cereale | null;
  distanceAppro?: number | null;
  autoconstruction?: YesNoPartial | null;
  participatif?: YesNoPartial | null;
  structCompl?: boolean | null;
  structComplNature?: StructureComplementaire | null;
  structComplInfos?: string | null;
  longMaxSansMurRefend?: number | null;
  noteCalcul?: boolean | null;
  nbrRangDeBottes?: number | null;
  integBaie?: IntegBaie | null;
  integBaieInfos?: string | null;
  supportAncrage?: SupportAncrage | null;
  supportAncrageInfos?: string | null;
  revetInt?: RevetInt | null;
  revetIntInfos?: string | null;
  revetExt?: RevetExt | null;
  revetExtInfos?: string | null;
  maitreDOuvrage?: string | null;
  maitreDOeuvre?: string | null;
  architecte?: string | null;
  bureauDEtudeStructure?: string | null;
  bureauControl?: string | null;
  entrepriseBottes?: string | null;
  entrepriseCharpente?: string | null;
  entrepriseEnduits?: string | null;
  descriptionProjet?: string | null;
  difficultees?: string | null;
  astuces?: string | null;
  divers?: string | null;
  contactNom?: string | null;
  contactMail?: string | null;
  contactPhone?: string | null;
  codePostal?: string | null;
  profilPublic?: boolean | null;
  conditionsAcceptees?: boolean | null;
  createdDate?: Date | null;
  lastModifiedDate?: Date | null;
  createdBy?: IUser;
}

export class Batiment implements IBatiment {
  constructor(
    public id?: number,
    public latitude?: number,
    public longitude?: number,
    public nomBatiment?: string | null,
    public photoPrincipaleContentType?: string | null,
    public photoPrincipale?: string | null,
    public photoPrincipaleLegende?: string | null,
    public photoPrincipaleDescription?: string | null,
    public photo1ContentType?: string | null,
    public photo1?: string | null,
    public photo1Legende?: string | null,
    public photo1Description?: string | null,
    public photo2ContentType?: string | null,
    public photo2?: string | null,
    public photo2Legende?: string | null,
    public photo2Description?: string | null,
    public photo3ContentType?: string | null,
    public photo3?: string | null,
    public photo3Legende?: string | null,
    public photo3Description?: string | null,
    public photo4ContentType?: string | null,
    public photo4?: string | null,
    public photo4Legende?: string | null,
    public photo4Description?: string | null,
    public photo5ContentType?: string | null,
    public photo5?: string | null,
    public photo5Legende?: string | null,
    public photo5Description?: string | null,
    public usageBatiment?: UsageBatiment | null,
    public usageBatimentInfos?: string | null,
    public cout?: number | null,
    public surfacePlancher?: number | null,
    public niveaux?: number | null,
    public travauxNeuf?: boolean | null,
    public travauxExtension?: boolean | null,
    public travauxRenov?: boolean | null,
    public travauxIte?: boolean | null,
    public travauxIti?: boolean | null,
    public constructionDebut?: Date | null,
    public constructionFin?: Date | null,
    public bottesTaille?: TaillesBottes | null,
    public bottesTailleInfos?: string | null,
    public bottesDensite?: number | null,
    public bottesCereale?: Cereale | null,
    public distanceAppro?: number | null,
    public autoconstruction?: YesNoPartial | null,
    public participatif?: YesNoPartial | null,
    public structCompl?: boolean | null,
    public structComplNature?: StructureComplementaire | null,
    public structComplInfos?: string | null,
    public longMaxSansMurRefend?: number | null,
    public noteCalcul?: boolean | null,
    public nbrRangDeBottes?: number | null,
    public integBaie?: IntegBaie | null,
    public integBaieInfos?: string | null,
    public supportAncrage?: SupportAncrage | null,
    public supportAncrageInfos?: string | null,
    public revetInt?: RevetInt | null,
    public revetIntInfos?: string | null,
    public revetExt?: RevetExt | null,
    public revetExtInfos?: string | null,
    public maitreDOuvrage?: string | null,
    public maitreDOeuvre?: string | null,
    public architecte?: string | null,
    public bureauDEtudeStructure?: string | null,
    public bureauControl?: string | null,
    public entrepriseBottes?: string | null,
    public entrepriseCharpente?: string | null,
    public entrepriseEnduits?: string | null,
    public descriptionProjet?: string | null,
    public difficultees?: string | null,
    public astuces?: string | null,
    public divers?: string | null,
    public contactNom?: string | null,
    public contactMail?: string | null,
    public contactPhone?: string | null,
    public codePostal?: string | null,
    public profilPublic?: boolean | null,
    public conditionsAcceptees?: boolean | null,
    public createdDate?: Date | null,
    public lastModifiedDate?: Date | null,
    public createdBy?: IUser
  ) {
    this.travauxNeuf = this.travauxNeuf ?? false;
    this.travauxExtension = this.travauxExtension ?? false;
    this.travauxRenov = this.travauxRenov ?? false;
    this.travauxIte = this.travauxIte ?? false;
    this.travauxIti = this.travauxIti ?? false;
    this.structCompl = this.structCompl ?? false;
    this.noteCalcul = this.noteCalcul ?? false;
    this.profilPublic = this.profilPublic ?? false;
    this.conditionsAcceptees = this.conditionsAcceptees ?? false;
  }
}
