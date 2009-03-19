/* This file is part of Sawa, the Scriptic-Java compiler
 * Copyright (C) 2009 Andre van Delft
 *
 * Sawa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package scriptic.tools;

import java.io.*;

   /*-----------------------------------------------------------------*/
   /*                                                                 */
   /*                        FieldDeclaration                         */
   /*                                                                 */
   /*-----------------------------------------------------------------*/

abstract class FieldDeclaration extends ModifierLanguageConstruct {
   public TypeDeclaration typeDeclaration;
   public Field           targetField() {return null;}
   public boolean         isDeprecated;

   public boolean declaresToThrowFromBody (CompilerEnvironment env, DataType throwable) throws IOException, CompilerError {return false;}

   /* ---------------------------- Presentation ------------------------ */

   public String getTargetPresentation () {
      if (targetField()==null) return "";
      return targetField().getPresentation();
   }
   public String getTargetDescription () {
      if (targetField()==null) return "";
      return targetField().getDescription();
   }
   public String getPresentation() {return getTargetPresentation();}
   public String getDescription () {return getTargetDescription ();}


   /* --------------------------- Constructors ------------------------- */

   public FieldDeclaration () { }
   public FieldDeclaration (FieldDeclaration declaration) { 
      super (declaration);
      this.typeDeclaration = declaration.typeDeclaration;
   }
}


